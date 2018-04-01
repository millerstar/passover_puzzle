package com.jigsaw.impl;

import java.io.File;

public class PuzzleImportPieces {

    // class members
    private static String PUZZLE_PIECES_PATH;
    private static final String inputFileName = "puzzlePiecesFile.txt";

    // getters
    public static String getPuzzlePiecesPath() {
        File file = new File("src/main/resources/puzzlePiecesFile.txt");
        PUZZLE_PIECES_PATH = file.getAbsolutePath();
        return PUZZLE_PIECES_PATH;
    }

    public static String getInputFileName() {
        return inputFileName;
    }
}
