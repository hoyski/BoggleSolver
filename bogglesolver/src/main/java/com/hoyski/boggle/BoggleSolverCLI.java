package com.hoyski.boggle;

import com.hoyski.wordfinder.WordFinder;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BoggleSolverCLI {
    public static void main(String[] args) {
        BoggleSolverCLI app = new BoggleSolverCLI();
        app.run();
    }

    private void run() {
        Scanner inputScanner = new Scanner(System.in);

        boolean done = false;

        while (!done) {
            System.out.print("Enter the 16 letters as a single string (Only enter Q for Qu). Or enter X to exit : ");
            String inputLine = inputScanner.nextLine();

            if (inputLine.equalsIgnoreCase("x")) {
                done = true;
            } else if (inputLine.length() != 16) {
                System.out.println("Input must be 16 letters long");
                System.out.println();
            } else {
                long start = System.currentTimeMillis();

                String[] letterStrs = inputLine.toUpperCase().split("");
                for (int i = 0; i < letterStrs.length; ++i) {
                    if (letterStrs[i].equals("Q")) {
                        letterStrs[i] = "QU";
                    }
                }
                List<String> letters = Arrays.asList(letterStrs);

                BoggleSolver boggleSolver = new BoggleSolver();
                List<WordAndChains> wordsAndChains = boggleSolver.getWords(letters);

                long end = System.currentTimeMillis();
                System.out.printf("Found %d words in %d ms%n%n", wordsAndChains.size(), (end - start));

                for (WordAndChains wordAndChains : wordsAndChains) {
                    for (int i = 0; i < wordAndChains.getLetterChains().size(); i++) {
                        System.out.printf("%10s - ", (i > 0) ? "" : wordAndChains.getWord());
                        LetterChain letterChain = wordAndChains.getLetterChains().get(i);
                        for (int p = 0; p < letterChain.getLetters().size(); ++p) {
                            if (p > 0) {
                                System.out.print(" -> ");
                            }
                            System.out.format("%d:%d", letterChain.getLetters().get(p).getX(), letterChain.getLetters().get(p).getY());
                        }
                        System.out.println();
                    }
                }
                System.out.println();
            }
        }
    }
}
