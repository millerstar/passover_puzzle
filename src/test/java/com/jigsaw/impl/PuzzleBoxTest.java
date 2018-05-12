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
        PuzzleBox puzzleBox1 = new PuzzleBox(puzzle);
        puzzleToCompare = puzzleBox1.getRotatedPiecesNewList();

        PuzzleBox puzzleBoxToCompare = new PuzzleBox(puzzleToCompare);

        assertTrue(puzzleBox1.equals(puzzleBoxToCompare));
    }


    @Test
    @DisplayName("Test constructor without rotation")
    void initialzationTestWithRotation() throws WrongElementsFormat {
        List<PuzzlePiece> puzzle = new ArrayList<>();
        List<PuzzlePiece> puzzleToCompare = new ArrayList<>();

        puzzle.add(piece1);
        PuzzleBox puzzleBox1 = new PuzzleBox(puzzle);
        puzzleToCompare = puzzleBox1.getRotatedPiecesNewList();
        //Rotation = true
        PuzzleBox puzzleBoxToCompare = new PuzzleBox(puzzleToCompare);
        puzzleBoxToCompare.getRotatedPiecesNewList();
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

        PuzzleBox box1 = new PuzzleBox(pieces);

        assertTrue(box1.allPiecesInBoxAreValid());
    }

    //Passed
    @Test
    @DisplayName("Test createRotatedPiecesTest in Puzzle Box - this test checks if the roteted pieace have been automatically generated ")
    public void createRotatedPieceTest() throws WrongElementsFormat {

        PuzzlePiece piece1 = new PuzzlePiece(0,1,1,-1,2);

        PuzzlePiece piece1Rotated = new PuzzlePiece(-1,1,0,1,2);

        List <PuzzlePiece> pieces = new ArrayList<>();
        pieces.add(piece1);

        PuzzleBox box1 = new PuzzleBox(pieces);

        List<PuzzlePiece> puzzlePieces = box1.getRotatedPiecesNewList();
        puzzlePieces.addAll(box1.getAllPiecesInBoard());
        assertTrue(puzzlePieces.contains(piece1));
        assertTrue(puzzlePieces.contains(piece1Rotated));
    }

    @Test
    @DisplayName("Test Negative case in createRotatedPiecesTest in Puzzle Box ")
    public void createRotatedPieceNegativeTest() throws WrongElementsFormat {
        List <PuzzlePiece> pieces = new ArrayList<>();
        PuzzlePiece piece1 = new PuzzlePiece(0,1,1,-1,1);
        //PuzzlePiece piece1RotatedShouldNotBeInBox = new PuzzlePiece(1,1,0,1,20);

        pieces.add(piece1);

        PuzzleBox box1 = new PuzzleBox(pieces);
        List<PuzzlePiece> puzzlePieces = new ArrayList<>();
        puzzlePieces.addAll(box1.getRotatedPiecesNewList());
        assertTrue(box1.countNumOfPiecesWithTheSameID(1) == 4);
    }

    //PASS
    @Test
    @DisplayName("Checks rotated pieces list size when given different pieces shapes ")
    public void checkIf() throws WrongElementsFormat {
        // rotation in Config must be false
        //Expected: no rotations
        PuzzlePiece rectanglePiece = new PuzzlePiece(0,0,0,0,1);
        //Expected: no rotations
        PuzzlePiece rectanglePiece2 = new PuzzlePiece(-1,-1,-1,-1,2);
        //Expected: 2 rotations
        PuzzlePiece trianglePiece = new PuzzlePiece(-1,-1,0,0,3);
        //Expected: 2 rotations
        PuzzlePiece trianglePiece2 = new PuzzlePiece(0,0,1,1,4);

        PuzzlePiece regularPiece1 = new PuzzlePiece(-1,0,1,-1,4);

        List<PuzzlePiece> pieces = new ArrayList<>();

        pieces.add(rectanglePiece);
        pieces.add(rectanglePiece2);
        pieces.add(trianglePiece);
        pieces.add(trianglePiece2);
        pieces.add(regularPiece1);


        PuzzleBox box1 = new PuzzleBox(pieces);
        List<PuzzlePiece> piecesWithRotation = box1.getRotatedPiecesNewList();


        assertTrue(pieces.size()==5);
        assertTrue(piecesWithRotation.size()==10);
    }

    @Test
    public void rotateOnePieceOneTime() throws WrongElementsFormat {
        //Expected: 3 rotations
        // rotation in Config must be false
        PuzzlePiece trianglePiece = new PuzzlePiece(-1,-1,0,0,3);
        List<PuzzlePiece> pieces = new ArrayList<>();
        pieces.add(trianglePiece);
        PuzzleBox box1 = new PuzzleBox(pieces);
        PuzzlePiece trianglePieceRotated90 = new PuzzlePiece(0,0,-1,-1,3);
        trianglePieceRotated90.setRotationDegree(90);
        List<PuzzlePiece> piecesWithRotation = box1.getRotatedPiecesNewList();

        // remove pieces that should be in list
        piecesWithRotation.removeIf(e -> e.isEqualByAllParams(trianglePiece));
        piecesWithRotation.removeIf(e -> e.isEqualByAllParams(trianglePieceRotated90));

        assertTrue(piecesWithRotation.size() == 0);
    }

    @Test
    public void rotateOnePiece3Times() throws WrongElementsFormat {
        //Expected: 3 rotations
        // rotation in Config must be false
        PuzzlePiece trianglePiece = new PuzzlePiece(-1,1,0,0,3);
        List<PuzzlePiece> pieces = new ArrayList<>();
        pieces.add(trianglePiece);
        PuzzleBox box1 = new PuzzleBox(pieces);
        PuzzlePiece trianglePiece90 = new PuzzlePiece(0,0,-1,1,3);
        trianglePiece90.setRotationDegree(90);
        PuzzlePiece trianglePiece180 = new PuzzlePiece(1,-1,0,0,3);
        trianglePiece180.setRotationDegree(180);
        PuzzlePiece trianglePiece270 = new PuzzlePiece(0,0,1,-1,3);
        trianglePiece270.setRotationDegree(270);
        List<PuzzlePiece> piecesWithRotation = box1.getRotatedPiecesNewList();

        // remove pieces that should be in list
        piecesWithRotation.removeIf(e -> e.isEqualByAllParams(trianglePiece90));
        piecesWithRotation.removeIf(e -> e.isEqualByAllParams(trianglePiece180));
        piecesWithRotation.removeIf(e -> e.isEqualByAllParams(trianglePiece270));

        // should contain only original piece
        assertTrue(piecesWithRotation.size() == 1);
    }

    @Test
    public void rotateOnePieceAllSidesEquals() throws WrongElementsFormat {
        //Expected: 0 rotations
        // rotation in Config must be false
        PuzzlePiece trianglePiece = new PuzzlePiece(-1,-1,-1,-1,3);
        List<PuzzlePiece> pieces = new ArrayList<>();
        pieces.add(trianglePiece);
        PuzzleBox box1 = new PuzzleBox(pieces);
        List<PuzzlePiece> piecesWithRotation = box1.getRotatedPiecesNewList();

        piecesWithRotation.removeIf(e -> e.isEqualByAllParams(trianglePiece));

        assertTrue(piecesWithRotation.size() == 0);
    }


    }
