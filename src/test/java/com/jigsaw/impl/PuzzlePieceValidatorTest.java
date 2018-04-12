package com.jigsaw.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PuzzlePieceValidatorTest {

    @Test
    @DisplayName("test isValidPiece")
    public void isValidPiece(PuzzlePiece pieceToValidate){
        PuzzlePieceValidator validPiece = new PuzzlePieceValidator();
        boolean isValidPiece = isValidPiece(validPiece);
        assertTrue(isValidPiece(validPiece));
    }

    }

    public boolean isPieceNotNull(PuzzlePiece pieceToValidate){
        return pieceToValidate==null;
    }

