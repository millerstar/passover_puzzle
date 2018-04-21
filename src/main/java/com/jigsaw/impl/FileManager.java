package com.jigsaw.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

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
        File outputFile = new File("src/main/resources/puzzleResultFile.txt");
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

            // print errors to file
            if (!errorArrayList.isEmpty()) {
                Collections.sort(errorArrayList);
                for (String error : errorArrayList) {
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
