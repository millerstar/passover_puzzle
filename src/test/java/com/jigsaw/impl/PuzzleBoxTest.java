package com.jigsaw.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 *  Author: Idan Agam
 *  Date:   01/04/2018
 */

public class PuzzleBoxTest {


    //Private class members
    private PuzzlePiece squarePiece = new PuzzlePiece(0,0,0,0,1);
    private PuzzlePiece piece1 = new PuzzlePiece(-1,-1,-1,-1,2);
    private PuzzlePiece piece2 = new PuzzlePiece(1,1,1,1,3);
    private PuzzlePiece piece3 = new PuzzlePiece(-1,0,0,-1,4);
    private PuzzlePiece nonValidPiece = new PuzzlePiece(2,0000,100,-80,-1);

    List<PuzzlePiece> puzzle = new ArrayList<>();


    @Test
    @DisplayName("Test constructor without rotation")
    void initialzationTestNoRotation() throws WrongElementsFormat {
        List<PuzzlePiece> puzzle = new ArrayList<>();
        List<PuzzlePiece> puzzleToCompare = new ArrayList<>();

        puzzle.add(piece1);
        PuzzleBox puzzleBox1 = new PuzzleBox(puzzle, false);
        puzzleToCompare = puzzleBox1.getAllPiecesInBoard();

        PuzzleBox puzzleBoxToCompare = new PuzzleBox(puzzleToCompare, false);

        assertTrue(puzzleBox1.equals(puzzleBoxToCompare));
    }


    @Test
    @DisplayName("Test constructor without rotation")
    void initialzationTestWithRotation() throws WrongElementsFormat {
        List<PuzzlePiece> puzzle = new ArrayList<>();
        List<PuzzlePiece> puzzleToCompare = new ArrayList<>();

        puzzle.add(piece1);
        PuzzleBox puzzleBox1 = new PuzzleBox(puzzle, false);
        puzzleToCompare = puzzleBox1.getAllPiecesInBoard();
        //Rotation = true
        PuzzleBox puzzleBoxToCompare = new PuzzleBox(puzzleToCompare, true);

        assertTrue(puzzleBox1.equals(puzzleBoxToCompare));
    }
    @Test
    @DisplayName("Test allPiecesInBoxAreValid in Puzzle Box ")
    public void allPiecesInBoxAreValidTest() throws WrongElementsFormat {
        PuzzlePiece piece1 = new PuzzlePiece(-1,-1,1,1,1);
        PuzzlePiece piece2 = new PuzzlePiece(0,1,1,-1,2);
        PuzzlePiece piece3 = new PuzzlePiece(-1,0,1,1,3);
        PuzzlePiece piece4 = new PuzzlePiece(1,1,0,0,4);

        List <PuzzlePiece> pieces = new ArrayList<>();
        pieces.add(piece1);
        pieces.add(piece2);
        pieces.add(piece3);
        pieces.add(piece4);

        PuzzleBox box1 = new PuzzleBox(pieces, false);
        assertTrue(box1.allPiecesInBoxAreValid());
    }

    //Passed
    @Test
    @DisplayName("Test createRotatedPiecesTest in Puzzle Box - this test checks if the roteted pieace have been automatically generated ")
    public void createRotatedPiecesTest() throws WrongElementsFormat {

        PuzzlePiece piece1 = new PuzzlePiece(0,1,1,-1,2);

        PuzzlePiece piece1Rotated = new PuzzlePiece(-1,1,0,1,2);

        List <PuzzlePiece> pieces = new ArrayList<>();
        pieces.add(piece1);

        PuzzleBox box1 = new PuzzleBox(pieces, true);
        List<PuzzlePiece> puzzlePieces = new ArrayList<>();
        puzzlePieces.addAll(box1.getAllPiecesInBoard());
        assertTrue(puzzlePieces.contains(piece1));
        assertTrue(puzzlePieces.contains(piece1Rotated));
    }

    //Passed BUT NEED TO TALK WITH MATVEY ABOUT EQUALS
    @Test
    @DisplayName("Test Negative case in createRotatedPiecesTest in Puzzle Box ")
    public void createRotatedPiecesNegativeTest() throws WrongElementsFormat {
        List <PuzzlePiece> pieces = new ArrayList<>();
        PuzzlePiece piece1 = new PuzzlePiece(0,1,1,-1,1);
        //PuzzlePiece piece1RotatedShouldNotBeInBox = new PuzzlePiece(1,1,0,1,20);

        pieces.add(piece1);

        PuzzleBox box1 = new PuzzleBox(pieces, true);
        List<PuzzlePiece> puzzlePieces = new ArrayList<>();
        puzzlePieces.addAll(box1.getAllPiecesInBoard());
        assertTrue(box1.countNumOfPiecesWithTheSameID(1) == 4);
    }

}
