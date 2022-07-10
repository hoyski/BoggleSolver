package com.hoyski.boggle;

import java.util.ArrayList;
import java.util.List;

public class WordAndChains {
    String word;
    List<LetterChain> letterChains;

    public WordAndChains(LetterChain letterChain) {
        this.word = letterChain.toString();
        letterChains = new ArrayList<>();
        letterChains.add(letterChain);
    }

    public String getWord() {
        return word;
    }

    public void addLetterChain(LetterChain letterChain) {
        letterChains.add(letterChain);
    }

    public List<LetterChain> getLetterChains() {
        return letterChains;
    }
}
