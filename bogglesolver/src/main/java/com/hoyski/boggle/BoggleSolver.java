package com.hoyski.boggle;

import com.hoyski.wordfinder.WordFinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class BoggleSolver {

    private WordFinder wordFinder;


    public BoggleSolver() {
        wordFinder = new WordFinder(false);
    }

    public List<WordAndChains> getWords(String lettersStr) {
        String[] letterStrs = lettersStr.toUpperCase().split("");
        for (int i = 0; i < letterStrs.length; ++i) {
            if (letterStrs[i].equals("Q")) {
                letterStrs[i] = "QU";
            }
        }
        List<String> letters = Arrays.asList(letterStrs);

        return getWords(letters);
    }


    /**
     * Returns all the words that can be made from the letters used to
     * construct this instance
     *
     * @return
     */
    public List<WordAndChains> getWords(List<String> letters) {
        Board board = new Board(letters);
        List<WordAndChains> words = new ArrayList<>();

        for (int y = 0; y < Board.BOARD_WIDTH_AND_HEIGHT; ++y) {
            for (int x = 0; x < Board.BOARD_WIDTH_AND_HEIGHT; ++x) {
                LetterChain chain = new LetterChain();
                chain = chain.addLetter(board.getLetter(x, y));
                findWords(board, words, chain);
            }
        }
        words.sort(new Comparator<WordAndChains>() {
            @Override
            public int compare(WordAndChains o1, WordAndChains o2) {
                if (o1.getWord().length() != o2.getWord().length()) {
                    // Sort shorter Strings before longer
                    return o1.getWord().length() - o2.getWord().length();
                } else {
                    // Sort equal length Strings alphabetically
                    return o1.getWord().compareTo(o2.getWord());
                }
            }
        });


        return words;
    }

    /**
     * Finds all the words on the board and stores them as LetterChains in <code>words</code>
     */
    private void findWords(Board board, List<WordAndChains> words, LetterChain curChain) {
        if (curChain == null) {
            return;
        }

        if (!wordsExistThatStartWith(curChain)) {
            // There are no words that start with this chain of letter. Stop searching
            return;
        }

        if (isWord(curChain) && curChain.size() >= 3) {
            boolean wordAlreadyFound = false;
            for (WordAndChains wordAndChains : words) {
                if (wordAndChains.getWord().equals(curChain.toString())) {
                    wordAlreadyFound = true;
                    wordAndChains.addLetterChain(curChain);
                }
            }
            if (!wordAlreadyFound) {
                words.add(new WordAndChains(curChain));
            }
        }

        Letter curLastLetter = curChain.getLastLetter();
        int endOfChainX = (curLastLetter == null) ? 0 : curLastLetter.getX();
        int endOfChainY = (curLastLetter == null) ? 0 : curLastLetter.getY();

        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                Letter newEndLetter = board.getLetter(endOfChainX + x, endOfChainY + y);
                if (newEndLetter != null) {
                    findWords(board, words, curChain.addLetter(newEndLetter));
                }
            }
        }
    }

    private boolean isWord(LetterChain chain) {
        return wordFinder.isWord(chain.toString());
    }

    private boolean wordsExistThatStartWith(LetterChain letterChain) {
        return wordFinder.wordsExistThatStartWith(letterChain.toString());
    }
}
