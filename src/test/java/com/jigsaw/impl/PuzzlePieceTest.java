package com.jigsaw.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 *  Author: Idan Agam
 *  Date:   01/04/2018
 */

public class PuzzlePieceTest {
    PuzzlePiece squarePiece = new PuzzlePiece(0,0,0,0,1);
    PuzzlePiece piece1 = new PuzzlePiece(-1,-1,-1,-1,2);
    PuzzlePiece piece2 = new PuzzlePiece(1,1,1,1,3);
    PuzzlePiece piece3 = new PuzzlePiece(-1,0,0,-1,4);
    PuzzlePiece nonValidPiece = new PuzzlePiece(2,0000,100,-80,-1);

    @Test
    @DisplayName("testGetSideLeft")
    public  void testGetSideLeft(){
        piece1.setSideLeft(1);
        assertEquals(piece1.getSideLeft(),1);
    }

    @Test
    @DisplayName("testGetSideRight")
    public  void testGetSideRight(){
        piece1.setSideRight(0);
        assertEquals(piece1.getSideRight(),0);
    }

    @Test
    @DisplayName("testGetSideTop")
    public  void testGetSideTop(){
        piece1.setSideTop(1);
        assertEquals(piece1.getSideTop(),1);
    }

    @Test
    @DisplayName("testGetSideTop")
    public  void testGetSideButtom(){
        piece1.setSideBottom(1);
        assertEquals(piece1.getSideBottom(),1);
    }
}
