package com.jigsaw.impl;

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

    @Test
    @DisplayName("test isValidPiece")
    public void isValidPiece() {
        PuzzlePiece validPiece = new PuzzlePiece(1,1,1,1,1);
        PuzzlePieceValidator p = new PuzzlePieceValidator();
        assertTrue(p.isPieceNotNull(validPiece));

    }

}

