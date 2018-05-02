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
     * generate random number for blending puzzle pieces result
     *
     * @param maxVal - last index piece in the pack
     * @return random number according the given bounds
     */
    private int getRandomNumber(int maxVal) {
        Random rand = new Random();
        return rand.nextInt((maxVal - 1) + 1) + 1;
    }

    /**
     * blend all puzzle pieces indexes
     */
    private void blendIndexToMap() {
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
    }


    public static void main(String[] args) throws InterruptedException {
        PuzzleGenerator pz = new PuzzleGenerator(4, 6, true);
        pz.blendIndexToMap();

        for (int i = 0; i < 15; i++) {
            System.out.print(pz.getRandomNumber(7) + " ");
        }

    }
}
