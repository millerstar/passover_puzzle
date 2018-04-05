package com.jigsaw.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static List<PuzzlePiece> getPuzzlePieces() throws IOException {
        List<String[]> elementsDetailsList = PuzzleImportPieces.getElementsDetails();
        List<PuzzlePiece> puzzlePieceList = new ArrayList<>();
        for (String[] element : elementsDetailsList) {
            int[] elementDetailsArray = Arrays.stream(element).mapToInt(Integer::parseInt).toArray();
            PuzzlePiece puzzlePiece = new PuzzlePiece(elementDetailsArray[1], elementDetailsArray[2], elementDetailsArray[3], elementDetailsArray[4], elementDetailsArray[0]);
            puzzlePieceList.add(puzzlePiece);
        }
        return puzzlePieceList;
    }

    // methods
    public static int getNumberOfElements() throws IOException {
        BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
        String currentLine = null;
        String numOfElementsStr = null;
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.contains("=")) {
                numOfElementsStr = currentLine.substring(currentLine.indexOf("=") + 1, currentLine.length()).trim();
                break;
            }
        }
        int numOfElements = Integer.parseInt(numOfElementsStr);
        return numOfElements;
    }

    public static List<String[]> getElementsDetails() throws IOException {
        BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
        String currentLine = null;
        List<String[]> pieceDataList = new ArrayList<>();
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.contains("NumElements")) {
                continue;
            }
            pieceDataList.add(currentLine.split(" "));
        }
        return pieceDataList;
    }
}
