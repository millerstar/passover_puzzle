package com.jigsaw.impl;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PuzzleImportPieces {

    // class members
    private static String PUZZLE_PIECES_PATH;
    private static final String INPUT_FILE_NAME = "puzzlePiecesFile.txt";
    private static final Path path = Paths.get(getPuzzlePiecesPath());

    List<PuzzlePiece> puzzlePieces = new ArrayList<>();
    private String numOfPices;

    // getters
    public static String getPuzzlePiecesPath() {
        File file = new File("src/main/resources/puzzlePiecesFile.txt");
        PUZZLE_PIECES_PATH = file.getAbsolutePath();
        return PUZZLE_PIECES_PATH;
    }

    public static String getInputFileName() {
        return INPUT_FILE_NAME;
    }

    public static Path getPath() {
        return path;
    }

    public List<PuzzlePiece> getPuzzlePieces() {
        return puzzlePieces;
    }
}
