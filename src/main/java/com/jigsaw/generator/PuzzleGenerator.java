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

    // default constructor - temp, test simple case
    public PuzzleGenerator() {
        this.rows = 2;
        this.columns = 2;
        this.isValidPuzzle = true;
        this.numOfCards = rows * columns;
        puzzlePieces = new ArrayList<>();
        puzzleIndexMap = new HashMap<>();
    }

    // getter

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
        bottom = 0;
        puzzlePiece = new PuzzlePiece(left, right, top, bottom, 1);
        puzzlePieces.add(puzzlePiece);

        for (int i = 1; i < numOfCards; i++) {

            // in case of one row
            if (rows == 1) {

                // in case of first piece on board
                if (puzzlePieces.get(i - 1).getSideRight() != 0) {
                    left = right * -1;
                } else {
                    left = 0;
                }
                right = getRandomNumber();


                if (i == numOfCards - 1) {
                    right = 0;
                }
                puzzlePiece = new PuzzlePiece(left, right, top, bottom, i + 1);
                puzzlePieces.add(puzzlePiece);
            }
        }
    }



    /**
     * blend all puzzle pieces indexes
     */
  /*  private void blendIndexToMap() {
        int newCardIndex = 0;
        for (int i = 1; i <= numOfCards; i++) {
            newCardIndex = getRandomNumber(numOfCards);
            if (!puzzleIndexMap.containsValue(newCardIndex)) {
                puzzleIndexMap.put(i, newCardIndex);
            } else {
                while (puzzleIndexMap.containsValue(newCardIndex)) {
                    newCardIndex = getRandomNumber(numOfCards);
                }
                puzzleIndexMap.put(i, newCardIndex);
            }
        }
    }*/
    public static void main(String[] args) throws InterruptedException {
        PuzzleGenerator pz = new PuzzleGenerator(1, 6, true);
        List<PuzzlePiece> p = new ArrayList<>();
        pz.generatePieces();
//        PuzzleGenerator pz2 = new PuzzleGenerator();
//        pz.blendIndexToMap();
        p = pz.getPuzzlePieces();
        System.out.println("tt");

    }
}
