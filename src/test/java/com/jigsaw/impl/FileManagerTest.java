package com.jigsaw.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileManagerTest {

    @Test
    @DisplayName("Is import file source exist")
    void isCorrectFileName() throws IOException {
        FileManager fileManager = new FileManager();
        List<PuzzlePiece> puzzlePieceList = fileManager.getPuzzlePieces();
        assertTrue(!fileManager.getErrorArrayList().contains("File name is not valid"), "The import file source exist");
    }

    @Test
    @DisplayName("Is NumElements appear")
    void isNumElementsAppear() throws IOException {
        FileManager fileManager = new FileManager();
        List<PuzzlePiece> puzzlePieceList = fileManager.getPuzzlePieces();
        assertTrue(!fileManager.getErrorArrayList().contains("The 'NumElements' was not display on first line in the data file"), "The 'NumElements' was not display");
    }

    @Test
    @DisplayName("Is equals sigh exists")
    void isEqualsSighExists() throws IOException {
        FileManager fileManager = new FileManager();
        List<PuzzlePiece> puzzlePieceList = fileManager.getPuzzlePieces();
        assertTrue(!fileManager.getErrorArrayList().contains("The equal sign was not display as expected"), "The equal sign was not display");
    }

    @Test
    @DisplayName("Is number of elements value correct")
    void isNumElementsValueCorrect() throws IOException {
        FileManager fileManager = new FileManager();
        List<PuzzlePiece> puzzlePieceList = fileManager.getPuzzlePieces();
        assertTrue(!fileManager.getErrorArrayList().contains("The number of puzzle pieces in incorrect"), "The number of puzzle pieces in incorrect");
    }
}