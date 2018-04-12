package com.jigsaw.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;

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
        clearResultFile();
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
            writer.newLine();
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
}
