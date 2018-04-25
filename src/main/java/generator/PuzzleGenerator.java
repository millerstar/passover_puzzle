package generator;


import com.jigsaw.impl.PuzzlePiece;

import java.util.*;

public class PuzzleGenerator {
    // class members
    private int rows;
    private int colunms;
    private List<PuzzlePiece> puzzlePieces;
    private Map<Integer,Integer> puzzleIndexMap;

    // constructor
    public PuzzleGenerator(int rows, int colunms) {
        this.rows = rows;
        this.colunms = colunms;
        puzzlePieces = new ArrayList<>();
        puzzleIndexMap = new HashMap<>();
    }

    // default constructor - temp, test simple case
    public PuzzleGenerator() {
        this.rows = 2;
        this.colunms = 2;
        puzzlePieces = new ArrayList<>();
        puzzleIndexMap = new HashMap<>();
    }

    // getter

    /**
     * get valid puzzle pieces
     * @return blended puzzle pieces with valid solution
     */
    public List<PuzzlePiece> getPuzzlePieces() {
        return puzzlePieces;
    }

    // methods
    // TODO: 4/25/2018 replace public to private
    /**
     * generate random number for blending puzzle pieces result
     * @param minVal - first index piece in the pack
     * @param maxVal - last index piece in the pack
     * @return random number according the given bounds
     */
    public int getRandomNumber(int minVal, int maxVal) {
        Random rand = new Random();
        int value = rand.nextInt((maxVal - minVal) + 1) + minVal;
        return value;
    }



    public static void main(String[] args) throws InterruptedException {
        PuzzleGenerator pz = new PuzzleGenerator();
        for (int i = 0; i < 15 ; i++) {
            System.out.print(pz.getRandomNumber(1,6) + " ");
        }

    }
}
