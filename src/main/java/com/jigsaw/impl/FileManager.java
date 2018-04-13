package com.jigsaw.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    // class members
    private String importPuzzleFileName;
    private String exportPuzzleFileName;
    private String importPuzzleFilePath;
    private String exportPuzzleFilePath;
    private String localPath = "src/main/resources/";
    private Path importAbsPath;
    private Path exportAbsPath;
    File inputFile;
    File outputFile;


    // constructor
    public FileManager() {
        this.importPuzzleFileName = "puzzlePiecesFile.txt";
        this.exportPuzzleFileName = "puzzleResultFile.txt";
        this.inputFile = new File(this.localPath + this.importPuzzleFileName);
        this.outputFile = new File(this.localPath + this.exportPuzzleFileName);
        this.importPuzzleFilePath = inputFile.getAbsolutePath();
        this.exportPuzzleFilePath = outputFile.getAbsolutePath();
        this.importAbsPath = Paths.get(this.importPuzzleFilePath);
        this.exportAbsPath = Paths.get(this.exportPuzzleFilePath);
    }

    // getters
    public String getImportPuzzleFileName() {
        return importPuzzleFileName;
    }

    public String getExportPuzzleFileName() {
        return exportPuzzleFileName;
    }

    // class methods
    public BufferedReader openFile(String fileName) {
        clearResultFile(); /* clear the output result file */
        BufferedReader reader = null;
        try {
            if (fileName.equalsIgnoreCase(this.importPuzzleFileName)) {
                reader = Files.newBufferedReader(this.importAbsPath, Charset.forName("UTF-8"));
            } else if (fileName.equalsIgnoreCase(this.exportPuzzleFileName)) {
                reader = Files.newBufferedReader(this.exportAbsPath, Charset.forName("UTF-8"));
            } else {
                throw new InvalidPathException(fileName, "File name is not valid");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return reader;
    }

    public void printToFile(String outputString) {
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(this.exportAbsPath, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
            writer.write(outputString);
//            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearResultFile() {
        try {
            Files.newBufferedWriter(this.exportAbsPath, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFile(BufferedReader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printPuzzleResult(PuzzlePiece[][] puzzlePiece) {
        if (puzzlePiece != null) {
            for (int i = 0; i < puzzlePiece.length; i++) {
                for (int j = 0; j < puzzlePiece[i].length; j++) {
                    printToFile(puzzlePiece[i][j].getPieceID() + " ");
                }
                printToFile("\n");
            }
        } else {
            // TODO: 4/13/2018  pull errors from aggregator and write to file
            printToFile("No valid solution was found for the puzzle");
        }

    }

    public List<String[]> getPieceDetailList() throws IOException {
        BufferedReader reader = openFile(this.importPuzzleFileName);
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

    public List<PuzzlePiece> getPuzzlePieces() throws IOException {
        List<String[]> elementsDetailsList = getPieceDetailList();
        List<PuzzlePiece> puzzlePieceList = new ArrayList<>();
        for (String[] element : elementsDetailsList) {
            int[] elementDetailsArray = Arrays.stream(element).mapToInt(Integer::parseInt).toArray();
            PuzzlePiece puzzlePiece = new PuzzlePiece(elementDetailsArray[1], elementDetailsArray[2], elementDetailsArray[3], elementDetailsArray[4], elementDetailsArray[0]);
            puzzlePieceList.add(puzzlePiece);
        }
        return puzzlePieceList;
    }
}
