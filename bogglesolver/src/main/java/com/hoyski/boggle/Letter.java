package com.hoyski.boggle;

/**
 * Letter encapsulates a letter on the game grid.
 */
public class Letter {
    private String letter; // String instead of char to support "Qu" as a single letter
    private int x;
    private int y;

    public Letter(int x, int y, String letter) {
        this.letter = letter;
        this.x = x;
        this.y = y;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
