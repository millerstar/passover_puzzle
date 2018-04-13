package com.jigsaw.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PuzzlePieceValidatorTest {

    @Test
    @DisplayName("test isValidPiece")
    public void isValidPiece() {
        PuzzlePiece validPiece = new PuzzlePiece(1,1,1,1,1);
        PuzzlePieceValidator p = new PuzzlePieceValidator();

        assertTrue(p.isValidPiece(validPiece));
    }


    @Test
    @DisplayName("test is piece is null")
    public boolean isPieceNotNull(PuzzlePiece pieceToValidate) {
        return pieceToValidate==null;
    }

    @Test
    @DisplayName("When given not valid piece")
    public void BadisValidPiece() {
        PuzzlePiece unValidPiece = new PuzzlePiece(1,1,1,1,-1);
        PuzzlePieceValidator p = new PuzzlePieceValidator();

        assertFalse(p.isValidPiece(unValidPiece));
    }
}

