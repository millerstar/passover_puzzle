package com.jigsaw.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 *  Author:         Idan Agam
 *  Date:           03/04/2018
 *  Fixed on date: 15/04/2018   ([14], [15])
 */

public class PuzzlePieceValidatorTest {

    private static PuzzlePieceValidator p;

    @BeforeAll
    public static void initialization(){
        p = new PuzzlePieceValidator();
    }

    @Test
    @DisplayName("test isValidPiece") //PASS
    public void isValidPiece() {
        PuzzlePiece validPiece = new PuzzlePiece(1,1,1,1,1);
        assertTrue(p.isValidPiece(validPiece));
    }

    @Test
    @DisplayName("test isValidPiece with large number in ID") //PASS
    public void isValidPieceLongID() {
        PuzzlePiece validPiece = new PuzzlePiece(1,1,1,1,100000);
        assertTrue(p.isValidPiece(validPiece));
    }

    @Test
    @DisplayName("test isValidPiece - negative - providing wrong piece coordinates") //PASS
    public void negativeIsValidPieceWrongCoordinates() {
        PuzzlePiece unValidPiece = new PuzzlePiece(30,1,1,1,1);
        assertFalse(p.isValidPiece(unValidPiece));
    }

    @Test
    @DisplayName("test isValidPiece - negative - providing wrong piece  wrong ID") //PASS
    public void negativeIsValidPieceWrongID() {
        PuzzlePiece unValidPiece = new PuzzlePiece(0,1,1,1,-2);
        assertTrue(p.isValidPiece(unValidPiece));
    }

    @Test
    @DisplayName("test isValidPiece - negative - providing wrong piece coordinates and the wrong coordinates ") //PASS
    public void negativeIsValidPieceWrongIDAndWrongCoordinates() {
        PuzzlePiece unValidPiece = new PuzzlePiece(-30,1,1,1,-2);
        assertFalse(p.isValidPiece(unValidPiece));
    }

    @Test
    @DisplayName("test isPieceASquareTest - with a square piece") //PASS
    public void isPieceASquareTest(){
        PuzzlePiece validPieceSquare = new PuzzlePiece(0,0,0,0,1);
        assertTrue(p.isPieceASquare(validPieceSquare));
    }

    @Test
    @DisplayName("test isPieceASquareTest - providing not a square piece - test number 1  ") //PASS
    public void negativeIsPieceASquareTest1(){
        PuzzlePiece validPieceSquare = new PuzzlePiece(1,0,1,0,1);
        assertFalse(p.isPieceASquare(validPieceSquare));
    }

    @Test
    @DisplayName("test isPieceASquareTest - providing not a square piece - test number 2  ") //PASS
    public void negativeIsPieceASquareTest2(){
        PuzzlePiece validPieceSquare = new PuzzlePiece(1,0,0,0,1);
        assertFalse(p.isPieceASquare(validPieceSquare));
    }

    @Test
    @DisplayName("test isPieceASquareTest - providing not a square piece - test number 3  ") //PASS
    public void negativeIsPieceASquareTest3(){
        PuzzlePiece validPieceSquare = new PuzzlePiece(0,1,0,-1,10);
        assertFalse(p.isPieceASquare(validPieceSquare));
    }



    }

