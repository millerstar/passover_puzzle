package com.jigsaw.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

/**
 * Author: Ilan
 */
//TODO interface
public class FileManager implements IPuzzleDataIO {

    // class members
    private Path importAbsPath;
    private Path exportAbsPath;
    private List<String> errorArrayList;

    // constructor
    public FileManager() {
        errorArrayList = new ArrayList<>();
        File inputFile = new File("src/main/resources/puzzlePiecesFile.txt");
        File outputFile = new File("src/main/resources/puzzleResultFile.txt");
        this.importAbsPath = Paths.get(inputFile.getAbsolutePath());
        this.exportAbsPath = Paths.get(outputFile.getAbsolutePath());
    }

    public FileManager(String fileFullPath) {
        File outputFile = new File(Config.getInstance().getOutputFilePosition());
        this.importAbsPath = Paths.get(fileFullPath);
        this.exportAbsPath = Paths.get(outputFile.getAbsolutePath());
        errorArrayList = new ArrayList<>();
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

    public List<String> getErrorArrayList() {
        return errorArrayList;
    }

    // class  service methods
    public void addErrorToList(String errorMessage) {
        errorArrayList.add(errorMessage);
    }

    private BufferedReader openFile(String fileName) {
        clearResultFile(); /* clear the output result file */
        BufferedReader reader = null;
        try {
            if (fileName.equalsIgnoreCase(this.getImportPuzzleFileName())) {
                reader = Files.newBufferedReader(this.importAbsPath, Charset.forName("UTF-8"));
            } else if (fileName.equalsIgnoreCase(this.getExportPuzzleFileName())) {
                reader = Files.newBufferedReader(this.exportAbsPath, Charset.forName("UTF-8"));
            } else {
                addErrorToList("File name is not valid");
                throw new InvalidPathException(fileName, "File name is not valid");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return reader;
    }

    private void closeFile(BufferedReader reader) {
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
        int rowNumber = 1;
        int numOfElements = 0;
        List<String[]> pieceDataList = new ArrayList<>();
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.contains("NumElements")) {
                // first row content validation
                if (rowNumber != 1) {
                    addErrorToList("The 'NumElements' was not display on first line in the data file");
                }
                if ((currentLine.indexOf("=") < 10) || (currentLine.indexOf("=", currentLine.indexOf("=") + 1) > 0)) {
                    addErrorToList("The equal sign was not display as expected");
                }
                numOfElements = Integer.parseInt(currentLine.substring(currentLine.indexOf("=") + 1, currentLine.length()).trim());
                continue;
            }
            pieceDataList.add(currentLine.split(" "));
            rowNumber++;
        }
        closeFile(reader);
        if (numOfElements != rowNumber - 1) {
            addErrorToList("The number of puzzle pieces in incorrect");
        }
        return pieceDataList;
    }


    @Override
    public void printPuzzleResult(Solver solver) {
        if (solver.isPuzzleSolved.get() && solver.validatePuzzleSolution()) {
            PuzzlePiece[][] board = solver.getCurrentBoard();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    PuzzlePiece currPiece = board[i][j];
                    if(currPiece.getRotationDegree() != 0){
                        printToFile("[" + currPiece.getPieceID() + "] ");
                    } else {
                        printToFile(currPiece.getPieceID() + " ");
                    }
                }
                printToFile("\n");
            }
        } else {
            // print errors to file
            if (!MessageAccumulator.getMassages().isEmpty()) {
                Collections.sort(MessageAccumulator.getMassages());
                for (String error : MessageAccumulator.getMassages()) {
                    printToFile(error);
                    printToFile("\n");
                }
            }
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
