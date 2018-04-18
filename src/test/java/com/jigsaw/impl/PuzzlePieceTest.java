package com.jigsaw.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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





}
