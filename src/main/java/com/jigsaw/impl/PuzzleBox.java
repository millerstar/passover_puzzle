package com.jigsaw.impl;


/**
 *
 *  Author: Idan Agam
 *  Date:   06/04/2018
 *   Fixed on date: 15/04/2018   ([26],[27])
 */

import java.util.ArrayList;
import java.util.List;

// this class contain pieces sorted by place they should take
public class PuzzleBox {
    private List<PuzzlePiece> allPiecesInBoard ;
    private List<PuzzlePiece> allPiecesInBoardWithRotation = new ArrayList<>();
    private boolean rotate = Config.getInstance().isRotate();

    private int numOfPieces;

    //C'tor overloading - with default rotation = 0
    public PuzzleBox(List<PuzzlePiece> pieces) throws WrongElementsFormat {
        this.allPiecesInBoard = pieces;
        if (this.rotate){
            getRotatedPiecesNewList();
        }
        numOfPieces = pieces.size();
        if (!allPiecesInBoxAreValid()) {
            throw new WrongElementsFormat();
        }
    }

    public int getNumOfPieces() {
        return numOfPieces;
    }

    private List<PuzzlePiece> getMalePiecesGroup() {
        List<PuzzlePiece> retVal = new ArrayList<PuzzlePiece>();

        for (PuzzlePiece piece : allPiecesInBoard) {
            if (piece.getSideLeft() == 1) {
                //Adding male piece
                retVal.add(piece);
            }
        }
        return retVal;
    }

    private List<PuzzlePiece> getFemalePiecesGroup() {
        List<PuzzlePiece> retVal = new ArrayList<PuzzlePiece>();

        for (PuzzlePiece piece : allPiecesInBoard) {
            if (piece.getSideLeft() == -1) {
                //Adding female piece
                retVal.add(piece);
            }
        }
        return retVal;
    }

    private List<PuzzlePiece> getStraightPiecesGroup() {
        List<PuzzlePiece> retVal = new ArrayList<PuzzlePiece>();

        for (PuzzlePiece piece : allPiecesInBoard) {
            if (piece.getSideLeft() == 0) {
                //Adding straight piece
                retVal.add(piece);
            }
        }
        return retVal;
    }

    public boolean isSumOfAllSidesZero() {
        int sum = 0;
        for (PuzzlePiece puzzleElement : allPiecesInBoard) {
            sum += puzzleElement.getSumOfAllSides();
        }
        if (sum != 0) {
            MessageAccumulator.addMassage("Cannot solve puzzle: sum of edges is not zero");
            return false;
        }
        return true;
    }

    public List<PuzzlePiece> getAllPiecesInBoard() {
        return allPiecesInBoard;
    }

    //TODO: Add writing errors to my List<String>
    //Make sure the puzzle is solvable
    private boolean validateThereAreAtLeast2CornersOnPuzzleBox() {
        int cornersCount = 0;
        for (PuzzlePiece piece : allPiecesInBoard) {
            if (piece.isPieceACorner()) {
                cornersCount++;
            }
        }
        if (cornersCount < 2) {
            MessageAccumulator.addMassage(String.format("Missing corners in puzzle , should be at least 2, got %d  ", cornersCount));
            return false;
        }
        return true;
    }

    //Checking number of pieces in board, etc..
    private boolean isPuzzleSolvable() {
        if (numOfPieces == 1) {
            return allPiecesInBoard.get(0).isSumOfAllSidesZero();
        }
        if ((numOfPieces == 0)) {
            MessageAccumulator.addMassage(String.format("We got zero pieces, can't solve puzzle "));
            return false;
        }

        return validateThereAreAtLeast2CornersOnPuzzleBox();
    }

    public List<PuzzlePiece> getPiecesGroupByType(int type) {
        List<PuzzlePiece> retVal = new ArrayList<PuzzlePiece>();
        for (PuzzlePiece piece : allPiecesInBoard) {
            if (piece.getSideLeft() == type) {
                retVal.add(piece);
            }
        }
        return retVal;
    }

    //Validate all puzzle pieces in the box
    public boolean allPiecesInBoxAreValid() throws WrongElementsFormat {
        int numberOfValidPiecesInBox = 0;
        PuzzlePieceValidator validator = new PuzzlePieceValidator();
        for (PuzzlePiece piece : allPiecesInBoard) {
            if (piece.isValid()) {
                numberOfValidPiecesInBox++;
            }
        }

//        if (!( basicValidations(validator) &&
//                isPuzzleSolvable() &&
//                isSumOfAllSidesZero())){
//            throw new WrongElementsFormat();
//        }

        return numberOfValidPiecesInBox == allPiecesInBoard.size();
    }

    private boolean basicValidations(PuzzlePieceValidator validator) {
        for (PuzzlePiece piece : allPiecesInBoard) {
            if (validator.validatePuzzlePiece(piece)) {
                MessageAccumulator.addMassage(String.format("Piece %d not valid", piece.getPieceID()));
                return false;
            }
        }
        return true;
    }

    public List<PuzzlePiece> getRotatedPiecesNewList() {
        int pieceOptionalRotations = 0;
        for (PuzzlePiece piece : allPiecesInBoard) {
            //Add the orginal piece
            allPiecesInBoardWithRotation.add(piece);

            pieceOptionalRotations = piece.getPieceOptionalRotations(piece);
            PuzzlePiece currPiece = piece;
            for (int i=0;i<pieceOptionalRotations;i++){
                PuzzlePiece rotatedPiece = currPiece.getNewRotetedPuzzlePiece();
                allPiecesInBoardWithRotation.add(rotatedPiece);
                currPiece = rotatedPiece;
            }
        }

        return allPiecesInBoardWithRotation;
    }

    public int countNumOfPiecesWithTheSameID(int ID){
    int numOfPiecesWithTheSameID = 0;

    for (PuzzlePiece piece: allPiecesInBoardWithRotation ) {
            if (piece.getPieceID() == ID){
                numOfPiecesWithTheSameID++;
            }
        }

    return numOfPiecesWithTheSameID;
    }

    public boolean isRotate() {
        return rotate;
    }

    public List<PuzzlePiece> getAllPiecesInBoardWithRotation() {
        return allPiecesInBoardWithRotation;
    }
}



