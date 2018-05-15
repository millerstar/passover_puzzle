package com.jigsaw.generator;

import com.jigsaw.impl.PuzzlePiece;

import java.util.*;

public class PuzzleGenerator {

    // class members
    private int rows;
    private int columns;
    private int numOfCards;
    private boolean isValidPuzzle;
    private List<PuzzlePiece> puzzlePieces;
    private Map<Integer, Integer> puzzleIndexMap;

    // constructor
    public PuzzleGenerator(int rows, int columns, boolean isValidPuzzle) {
        this.rows = rows;
        this.columns = columns;
        this.numOfCards = rows * columns;
        this.isValidPuzzle = isValidPuzzle;
        puzzlePieces = new ArrayList<>();
        puzzleIndexMap = new HashMap<>();
    }

    /**
     * get valid puzzle pieces
     *
     * @return blended puzzle pieces with valid solution
     */
    public List<PuzzlePiece> getPuzzlePieces() {
        Collections.shuffle(puzzlePieces);
        return puzzlePieces;
    }

    // methods

    /**
     * generate random number for puzzle piece     *
     *
     * @return random number between -1 to 1 (-1,0,1)
     */
    private int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt((1 + 1) + 1) - 1;
    }

    public void generatePieces() {
        int left, right, top, bottom;
        PuzzlePiece puzzlePiece;
        left = 0;
        right = getRandomNumber();
        top = 0;
        if (rows == 1) {
            bottom = 0;
        } else {
            bottom = getRandomNumber();
        }
        // create the first puzzle piece
        puzzlePiece = new PuzzlePiece(left, right, top, bottom, 1);
        puzzlePieces.add(puzzlePiece);

        for (int i = 1; i < numOfCards; i++) {

            // in case of one row puzzle
            if (rows == 1) {
                // find left mach for the next piece
                if (puzzlePieces.get(i - 1).getSideRight() != 0) {
                    left = right * -1;
                } else {
                    left = 0;
                }
                right = getRandomNumber();

                // in case of last piece
                if (i == numOfCards - 1) {
                    right = 0;
                }
                puzzlePiece = new PuzzlePiece(left, right, top, bottom, i + 1);
                puzzlePieces.add(puzzlePiece);
                // in case of multi rows puzzle
            } else {
                // first line in multiple rows puzzle
                if (i < columns) {
                    if (puzzlePieces.get(i - 1).getSideRight() != 0) {
                        left = right * -1;
                    } else {
                        left = 0;
                    }
                    right = getRandomNumber();
                    bottom = getRandomNumber();

                    // in case of last piece in a row
                    if (((i + 1) % columns) == 0) {
                        right = 0;
                    }
                    puzzlePiece = new PuzzlePiece(left, right, top, bottom, i + 1);
                    puzzlePieces.add(puzzlePiece);
                }
                // in case of last row
                else if ((i >= (rows - 1) * columns) && (i < numOfCards)) {
                    if (puzzlePieces.get(i - 1).getSideRight() != 0) {
                        left = right * -1;
                    } else {
                        left = 0;
                    }
                    right = getRandomNumber();
                    if (puzzlePieces.get(i - columns).getSideBottom() != 0) {
                        top = puzzlePieces.get(i - columns).getSideBottom() * -1;
                    } else {
                        top = 0;
                    }
                    bottom = 0;
                    // in case of last piece
                    if (i == numOfCards - 1) {
                        right = 0;
                    }
                    puzzlePiece = new PuzzlePiece(left, right, top, bottom, i + 1);
                    puzzlePieces.add(puzzlePiece);
                // in case of middle row
                } else {
                    if (puzzlePieces.get(i - 1).getSideRight() != 0) {
                        left = right * -1;
                    } else {
                        left = 0;
                    }
                    right = getRandomNumber();
                    if (puzzlePieces.get(i - columns).getSideBottom() != 0) {
                        top = puzzlePieces.get(i - columns).getSideBottom() * -1;
                    } else {
                        top = 0;
                    }
                    bottom = getRandomNumber();
                    // in case of last piece in a row
                    if (((i + 1) % columns) == 0) {
                        right = 0;
                    }
                    puzzlePiece = new PuzzlePiece(left, right, top, bottom, i + 1);
                    puzzlePieces.add(puzzlePiece);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PuzzleGenerator pz = new PuzzleGenerator(5, 5, true);
        List<PuzzlePiece> p = new ArrayList<>();
        pz.generatePieces();
        p = pz.getPuzzlePieces();
//        System.out.println("cool");
    }
}
