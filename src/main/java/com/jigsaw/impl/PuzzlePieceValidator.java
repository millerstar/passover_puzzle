package com.jigsaw.impl;

/**
 *  Author: Idan Agam
 *  Date:   01/04/2018
 */

public class PuzzlePieceValidator {

    //Private class members
    private PuzzlePiece piece;
    MessageAccumulator messageAccumulator = new MessageAccumulator();

    //public methods
    public boolean isValidPiece(PuzzlePiece pieceToValidate){
        int size;
        size =  pieceToValidate.getSumOfAllSides();
        if (size>4 || size<0 || pieceToValidate==null){
            messageAccumulator.addMassage(String.format("Not A valid piece! Piece ID = ",pieceToValidate.getPieceID()));
            return false;
        }
        int sideLeft = pieceToValidate.getSideLeft();
        int sideRight = pieceToValidate.getSideRight();
        int sideTop = pieceToValidate.getSideTop();
        int sideBottom = pieceToValidate.getSideBottom();

        if (sideLeft>1||sideLeft<0||sideRight>1||sideRight<0||sideTop>1||sideTop<0||sideBottom>1||sideBottom<0){
            messageAccumulator.addMassage(String.format("Not A valid piece! Piece ID = ",pieceToValidate.getPieceID()) +" Has wrong coordinates ");
            return false;// we got the wrong coordinates
        }
        return true;
    }

    public boolean isPieceNotNull(PuzzlePiece pieceToValidate){
        return pieceToValidate!=null;
    }

    public boolean isPieceASquare(PuzzlePiece pieceToValidate){
        int sideLeft = pieceToValidate.getSideLeft();
        int sideRight = pieceToValidate.getSideRight();
        int sideTop = pieceToValidate.getSideTop();
        int sideBottom = pieceToValidate.getSideBottom();

        if (sideLeft==0 && sideRight==0 && sideTop==0 && sideBottom==0) {
            return true;
        }
        return false;
        }

        public boolean validatePuzzlePiece (PuzzlePiece pieceToValidate){
            return isPieceNotNull(pieceToValidate)&& isValidPiece(pieceToValidate);
        }




    }
