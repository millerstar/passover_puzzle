package com.jigsaw.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 *  Author:         Idan Agam
 *  Date:           01/04/2018
 *  Fixed on date: 15/04/2018   ([13])
 */

public class PuzzlePieceTest {
    PuzzlePiece squarePiece = new PuzzlePiece(0,0,0,0,1);
    PuzzlePiece piece1 = new PuzzlePiece(-1,-1,-1,-1,2);
    PuzzlePiece piece2 = new PuzzlePiece(1,1,1,1,3);
    PuzzlePiece piece3 = new PuzzlePiece(-1,0,0,-1,4);
    PuzzlePiece nonValidPiece = new PuzzlePiece(2,0000,100,-80,-1);


    @Test
    @DisplayName("Test isSumOfAllSidesZero method")
    public void testIsSumOfAllSidesZero(){
        assertEquals(squarePiece.isSumOfAllSidesZero(), true);
    }

    @Test
    @DisplayName("Test getSumOfAllSides method")
    public void testGetSumOfAllSides(){
        assertEquals(squarePiece.getSumOfAllSides(),0);
    }

    @Test
    @DisplayName("Test isEqualByShape method")
    public void isEqualByShapeTest(){
        PuzzlePiece piece1 = new PuzzlePiece(0,1,1,-1,2);
        PuzzlePiece piece2 = new PuzzlePiece(0,1,1,-1,20);
        assertTrue((piece1.isEqualByShape(piece2)));
    }
//BUG??
    @Test
    @DisplayName("Test rotation ability")
    public void rotationTest(){
        PuzzlePiece piece1 = new PuzzlePiece(0,1,1,-1,2);
        PuzzlePiece piece1Rotated = piece1.getNewRotetedPuzzlePiece();
        PuzzlePiece piece2RotatedForCompare = new PuzzlePiece(-1,1,0,1,2);

        System.out.println(piece2RotatedForCompare.isEqualByShape(piece1Rotated));
        assertTrue((piece2RotatedForCompare.isEqualByShape(piece1Rotated)));
    }

    @Test
    @DisplayName("Test that the rotated piece has the same coordinates sum than the original piece")
    public void rotationCheckPiecesSumOfCoordinatesTest(){
        PuzzlePiece piece1 = new PuzzlePiece(0,1,1,-1,2);
        PuzzlePiece piece1Rotated = piece1.getNewRotetedPuzzlePiece();

        assertTrue((piece1Rotated.getSumOfAllSides() == piece1.getSumOfAllSides()));


    }


    }
