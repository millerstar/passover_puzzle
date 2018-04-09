package com.jigsaw.impl;


/**
 *
 *  Author:
 *  Date:   06/04/2018
 */


import javafx.scene.control.cell.CheckBoxListCell;

import java.util.ArrayList;
import java.util.List;

// this class contain pieces sorted by place they should take
public class PuzzleBox {
    private List<PuzzlePiece> straightRightLeftPieces = new ArrayList<>() ;
    private List<PuzzlePiece> maleRightLeftPieces = new ArrayList<>();
    private List<PuzzlePiece> femaleRightLeftPieces = new ArrayList<>();
    private List<PuzzlePiece> allPiecesInBoard = new ArrayList<>();

    private int numOfPieces;

    public PuzzleBox(List<PuzzlePiece> pieces) {
        this.allPiecesInBoard = pieces;
        straightRightLeftPieces = getStraightPiecesGroup();
        maleRightLeftPieces = getMalePiecesGroup();
        femaleRightLeftPieces = getFemalePiecesGroup();


    }

    public int getNumOfPieces() {
        return numOfPieces;
    }

    public List<PuzzlePiece> getMalePiecesGroup(){
        List<PuzzlePiece> retVal = new ArrayList<PuzzlePiece>();

        for (PuzzlePiece piece :allPiecesInBoard) {
            if (piece.getSideLeft()==1) {
                //Adding male piece
                retVal.add(piece);
            }
        }
        return retVal;
    }

    public List<PuzzlePiece> getFemalePiecesGroup(){
        List<PuzzlePiece> retVal = new ArrayList<PuzzlePiece>();

        for (PuzzlePiece piece :allPiecesInBoard) {
            if (piece.getSideLeft()==-1) {
                //Adding female piece
                retVal.add(piece);
            }
        }
        return retVal;
    }

    public List<PuzzlePiece> getStraightPiecesGroup(){
        List<PuzzlePiece> retVal = new ArrayList<PuzzlePiece>();

        for (PuzzlePiece piece :allPiecesInBoard) {
            if (piece.getSideLeft()==0) {
                //Adding straight piece
                retVal.add(piece);
            }
        }
        return retVal;
    }

    public boolean isSumOfAllSidesZero() {
        int sum = 0;
        for (PuzzlePiece puzzleElement : allPiecesInBoard) {
            sum = puzzleElement.getSumOfAllSides();
        }
        if (sum != 0) {
            return false;
        }
        return true;
    }



}


