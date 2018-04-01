package com.jigsaw.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleImportPiecesTest {

    public Path getFilePath() {
        Path path = Paths.get(PuzzleImportPieces.getPuzzlePiecesPath());
        return  path;
    }

    @Test
    @DisplayName("Input file exist")
    void isFileExist() {
        Path path = getFilePath();
        assertTrue(Files.exists(path), "The input file '" + PuzzleImportPieces.getInputFileName() + "' was not found!");
    }

    @Test
    @DisplayName("Correct file name")
    void isCorrectFileName() {
        Path path = getFilePath();
        String actualFileName = path.getFileName().toString();
        assertEquals(PuzzleImportPieces.getInputFileName(),actualFileName,"The actual file name '" + actualFileName + "' is incorrect!");

    }
}