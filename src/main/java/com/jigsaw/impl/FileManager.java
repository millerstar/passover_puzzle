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

//TODO interface
public class FileManager implements IPuzzleDataIO {

    // class members
    private Path importAbsPath;
    private Path exportAbsPath;

    // constructor
    public FileManager() {
        File inputFile = new File("src/main/resources/puzzlePiecesFile.txt");
        File outputFile = new File("src/main/resources/puzzleResultFile.txt");
        this.importAbsPath = Paths.get(inputFile.getAbsolutePath());
        this.exportAbsPath = Paths.get(outputFile.getAbsolutePath());
    }

    public FileManager(String fileFullPath) {
        File outputFile = new File("src/main/resources/puzzleResultFile.txt");
        this.importAbsPath = Paths.get(fileFullPath);
        this.exportAbsPath = Paths.get(outputFile.getAbsolutePath());
    }

    // getters
    public String getImportPuzzleFileName() {
        Path importFileName = importAbsPath.getFileName();
        return importFileName.toString();
    }

    public String getExportPuzzleFileName() {
        Path exportFileName = exportAbsPath.getFileName();
        return exportFileName.toString();
    }

    // class  service methods
    public BufferedReader openFile(String fileName) {
        clearResultFile(); /* clear the output result file */
        BufferedReader reader = null;
        try {
            if (fileName.equalsIgnoreCase(this.getImportPuzzleFileName())) {
                reader = Files.newBufferedReader(this.importAbsPath, Charset.forName("UTF-8"));
            } else if (fileName.equalsIgnoreCase(this.getExportPuzzleFileName())) {
                reader = Files.newBufferedReader(this.exportAbsPath, Charset.forName("UTF-8"));
            } else {
                throw new InvalidPathException(fileName, "File name is not valid");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return reader;
    }

    public void closeFile(BufferedReader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printToFile(String outputString) {
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

    private void clearResultFile() {
        try {
            Files.newBufferedWriter(this.exportAbsPath, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String[]> getPieceDetailList() throws IOException {
        BufferedReader reader = openFile(getImportPuzzleFileName());
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


    @Override
    public void printPuzzleResult(Solver solver) {
        if (solver.isPuzzleSolved && solver.validatePuzzleSolution()) {
            PuzzlePiece[][] board = solver.getCurrentBoard();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    printToFile(board[i][j].getPieceID() + " ");
                }
                printToFile("\n");
            }
        } else {
            // TODO: 4/13/2018  pull errors from aggregator and write to file
            printToFile("No valid solution was found for the puzzle");
        }
    }

    @Override
    public List<PuzzlePiece> getPuzzlePieces() throws IOException {
        List<String[]> elementsDetailsList = getPieceDetailList();
        List<PuzzlePiece> puzzlePieceList = new ArrayList<>();
        int sideLeft, sideTop, sideRight, sideBottom, id;
        for (String[] element : elementsDetailsList) {
            int[] elementDetailsArray = Arrays.stream(element).mapToInt(Integer::parseInt).toArray();
            id = elementDetailsArray[0];
            sideLeft = elementDetailsArray[1];
            sideTop = elementDetailsArray[2];
            sideRight = elementDetailsArray[3];
            sideBottom = elementDetailsArray[4];
            PuzzlePiece puzzlePiece = new PuzzlePiece(sideLeft, sideRight, sideTop, sideBottom, id);
            puzzlePieceList.add(puzzlePiece);
        }
        return puzzlePieceList;
    }
}
