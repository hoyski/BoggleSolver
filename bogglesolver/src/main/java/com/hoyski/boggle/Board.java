package com.hoyski.boggle;

import java.util.ArrayList;
import java.util.List;

/**
 * Board represents a 4x4 grid of Letters. The letters' x:y positions start in the upper left
 * at 0:0. x is the horizontal and y is the vertical.
 */
public class Board {
    public static int BOARD_WIDTH_AND_HEIGHT = 4;

    private List<Letter> board;

    public Board() {
        board = new ArrayList<>(BOARD_WIDTH_AND_HEIGHT * BOARD_WIDTH_AND_HEIGHT);
        for (int i = 0; i < BOARD_WIDTH_AND_HEIGHT * BOARD_WIDTH_AND_HEIGHT; i++) {
            board.add(new Letter(0, 0, ""));
        }
    }

    public Board(List<String> letters) {
        this();

        int requiredSize = Board.BOARD_WIDTH_AND_HEIGHT * Board.BOARD_WIDTH_AND_HEIGHT;

        if (letters.size() != requiredSize) {
            throw new IllegalArgumentException("BoggleSolver must be constructed with exactly " + requiredSize + " letters");
        }

        int index = 0;
        for (int y = 0; y < BOARD_WIDTH_AND_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH_AND_HEIGHT; x++) {
                setLetter(x, y, letters.get(index));
                index++;
            }
        }
    }

    /**
     * Returns the Letter at the given x:y position or <code>null</code> if the position is
     * off the board
     */
    public Letter getLetter(int x, int y) {
        if (x < 0 || x >= BOARD_WIDTH_AND_HEIGHT || y < 0 || y >= BOARD_WIDTH_AND_HEIGHT) {
            return null;
        } else {
            return board.get((x * BOARD_WIDTH_AND_HEIGHT) + y);
        }
    }

    public void setLetter(int x, int y, String letterOnTile) {
        Letter letter = new Letter(x, y, letterOnTile);
        board.set((x * BOARD_WIDTH_AND_HEIGHT) + y, letter);
    }

}
