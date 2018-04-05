package com.jigsaw.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleImportPiecesTest {

    @Test
    @DisplayName("Input file exist")
    void isFileExist() {
        Path path = PuzzleImportPieces.getPath();
        assertTrue(Files.exists(path), "The input file '" + PuzzleImportPieces.getInputFileName() + "' was not found!");
    }

    @Test
    @DisplayName("Correct file name")
    void isCorrectFileName() {
        Path path = PuzzleImportPieces.getPath();
        String actualFileName = path.getFileName().toString();
        assertEquals(PuzzleImportPieces.getInputFileName(), actualFileName, "The actual file name '" + actualFileName + "' is incorrect!");
    }

    @Test
    @DisplayName("Is NumElements appear")
    void isNumElementsAppear() throws IOException {
        Path path = PuzzleImportPieces.getPath();
        BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
        String currentLine = null;
        boolean isEmptyLine;
        String actualString = null;
        String expectedVal = "NumElements";
        while ((currentLine = reader.readLine()) != null) {
            isEmptyLine = currentLine.isEmpty();
            currentLine = currentLine.trim();
            if ((isEmptyLine == false && !currentLine.contains("NumElements"))) {
                actualString = currentLine.trim();
                break;
            }
            if (!isEmptyLine && currentLine.contains("NumElements")) {
                actualString = currentLine.substring(0, currentLine.indexOf("="));
                break;
            }
        }
        assertEquals(expectedVal, actualString, "The '" + expectedVal + "' was not display in the file");
    }

    @Test
    @DisplayName("Is NumElements in first line")
    void isNumElementsFirst() throws IOException {
        Path path = PuzzleImportPieces.getPath();
        BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
        String currentLine = null;
        int counter = 1;
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.contains("NumElements")) {
                break;
            } else {
                counter++;
            }
        }
        assertTrue(counter == 1, "The 'NumElements' was not display in the first line");
    }

    @Test
    @DisplayName("Is equals sigh exists")
    void isEqualsExist() throws IOException {
        Path path = PuzzleImportPieces.getPath();
        BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
        String currentLine = null;
        int equalIndex = 0;
        int nextEqualIndex = 0;
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.contains("NumElements")) {
                equalIndex = currentLine.indexOf("=");
                nextEqualIndex = currentLine.indexOf("=", equalIndex + 1);
                break;
            }
        }
        assertTrue(equalIndex > 10 && nextEqualIndex < 1, "The equal sign was not display as expected");
    }

    @Test
    @DisplayName("Is number of elements value correct")
    void isNumElementsValueCorrect() throws IOException {
        int numOfElements = PuzzleImportPieces.getNumberOfElements();
        assertTrue(numOfElements > 1, "The number od puzzle pieces in incorrect");
    }

    @Test
    @DisplayName("Element correct format")
    void elementCorrectFormat() throws IOException {
        List<String[]> elementsDetailsList = PuzzleImportPieces.getElementsDetails();
        for (String[] element : elementsDetailsList) {
            // piece format size
            assertTrue(element.length == 5, "The piece format size is incorrect, actual: " + element.length + " expected: 5");

            // index value
            assertTrue(Integer.parseInt(element[0]) > 0, "The first index digit is incorrect");

            // valid size value
            for (int i = 1; i < element.length; i++) {
                assertTrue(Integer.parseInt(element[i]) > -2 && Integer.parseInt(element[i]) < 2, "The piece size value " + element[i] + " is incorrect");
            }
        }
    }

    @Test
    void isElementsInRange() throws IOException {
        List<String[]> elementsDetailsList = PuzzleImportPieces.getElementsDetails();
        int numOfElements = PuzzleImportPieces.getNumberOfElements();
        assertEquals(elementsDetailsList.size(), numOfElements,"The number of elements in the file is incorrect");
    }
}