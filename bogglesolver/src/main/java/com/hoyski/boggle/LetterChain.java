package com.hoyski.boggle;

import java.util.ArrayList;
import java.util.List;

/**
 * LetterChain represents a list of Letters. It ensures that no Letters repeat
 * as far as their x:y position is concerned
 */
public class LetterChain {
    private List<Letter> letters;

    public LetterChain() {
        letters = new ArrayList<>();
    }

    public LetterChain(LetterChain other, Letter letterToAppend) {
        letters = new ArrayList<>();
        for (Letter letter : other.letters) {
            letters.add(letter);
        }
        letters.add(letterToAppend);
    }

    /**
     * Returns a new LetterChain with <code>letter</code> appended.
     * Returns <code>null</code> if this LetterChain already contains <code>letter</code>
     *
     * @param letter Letter to add
     * @return See above
     */
    public LetterChain addLetter(Letter letter) {
        // Ensure Letter isn't already on the chain
        for (Letter curLetter : letters) {
            if (curLetter.getX() == letter.getX() && curLetter.getY() == letter.getY()) {
                return null;
            }
        }

        return new LetterChain(this, letter);
    }

    public Letter getLastLetter() {
        if (letters.size() == 0) {
            return null;
        }
        return letters.get(letters.size() - 1);
    }

    public int size() {
        return letters.size();
    }

    public List<Letter> getLetters() {
        return letters;
    }

    @Override
    public String toString() {
        StringBuilder chainAsStr = new StringBuilder();
        for (Letter letter : letters) {
            chainAsStr.append(letter.getLetter());
        }
        return chainAsStr.toString();
    }
}
