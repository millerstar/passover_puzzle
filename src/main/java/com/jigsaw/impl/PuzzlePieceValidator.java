package com.jigsaw.impl;

public class PuzzlePieceValidator {

    //Private class members
    PuzzlePiece piece;

    public boolean isValidPiece(PuzzlePiece pieceToValidate){
        int size;
        size =  pieceToValidate.getPieceCoordinates().length;
        if (size>4 || size<=0){
            return false; // We got wrong amount of coordinates
        }
        int topLeft = pieceToValidate.getTopLeft();
        int topRight = pieceToValidate.getTopRight();
        int bottomLeft = pieceToValidate.getBottomLeft();
        int bottomRight = pieceToValidate.getBottomRight();

        if (topLeft>1||topLeft<0||topRight>1||topRight<0||bottomLeft>1||bottomLeft<0||bottomRight>1||bottomRight<0){
            return false;// we got the wrong coordinates
        }
        return true;
    }

    public boolean isPieceNotNull(PuzzlePiece pieceToValidate){
        return pieceToValidate==null;
    }

    public boolean isPiaceSquar(PuzzlePiece pieceToValidate){
        int topLeft = pieceToValidate.getTopLeft();
        int topRight = pieceToValidate.getTopRight();
        int bottomLeft = pieceToValidate.getBottomLeft();
        int bottomRight = pieceToValidate.getBottomRight();

        if (topLeft==0&&topRight==0&&bottomLeft==0&&bottomRight==0) {
            return true;
        }
        return false;
        }




    }
