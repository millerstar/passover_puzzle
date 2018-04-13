package com.jigsaw.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 *  Author:
 *  Date:   01/04/2018
 */

public class Solver {

    private List<PuzzlePiece[][]> boards = new ArrayList<>(); // todo add class for board, add members that save prev and current possition
    private PuzzlePiece[][] currentBoard;
    private PuzzleBox puzzleBox;
    private List<PuzzlePiece> poolOfPieces = new ArrayList<>();
    private Map<String, List<PuzzlePiece>> slotToPieces= new HashMap<>();
    private int row;
    private int col;

    public Solver(PuzzleBox puzzleBox) {
        this.puzzleBox = puzzleBox;
        this.poolOfPieces.addAll(puzzleBox.getAllPiecesInBoard());
    }

    public PuzzleBox getPuzzleBox() {
        return puzzleBox;
    }

    public void createPossibleBoards(){
        int numOfPieces = puzzleBox.getAllPiecesInBoard().size();
        for( int i = numOfPieces/2; i >= 2; i--){
            if(numOfPieces % i == 0){
                PuzzlePiece[][] board = new PuzzlePiece[i][numOfPieces/i];
                boards.add(board);
            }
        }
    }

    public void solvePuzzle(){
        boolean isSolved = false;
        for(PuzzlePiece[][] board: boards){
            row = 0;
            col = 0;
            currentBoard = board;
            System.out.println(String.format("Starting to check board %s X %s",currentBoard.length, currentBoard[0].length));
            while(true){
                if (row < 0){
                    break;
                }
                if (row == currentBoard.length){
                    isSolved = true;
                    break;
                }
                String slotKey = Integer.toString(row) + Integer.toString(col);
                if (!slotToPieces.containsKey(slotKey)) {
                    slotToPieces.put(slotKey, piecesForSlot());
                }

                List<PuzzlePiece> possiblePiecesForSlot = slotToPieces.get(slotKey);
                if(possiblePiecesForSlot.isEmpty()){
                    prepareStepBack(slotKey, possiblePiecesForSlot);
                    stepBack();
                    continue;
                }
                // remove piece from possible solution to prevent same piece twice on board
                // todo extract to method or add flag to PuzzlePiece isOnBoard
                while(!poolOfPieces.contains(possiblePiecesForSlot.get(0))){
                    possiblePiecesForSlot.remove(possiblePiecesForSlot.get(0));
                    if(possiblePiecesForSlot.isEmpty()){
                        break;
                    }
                }

                if (possiblePiecesForSlot.size() > 0) {
                    PuzzlePiece pieceToBePlaced = possiblePiecesForSlot.get(0);
                    returnPieceToPool();
                    currentBoard[row][col] = pieceToBePlaced;
                    System.out.println(pieceToBePlaced + " added");
                    poolOfPieces.remove(pieceToBePlaced);
                    possiblePiecesForSlot.remove(pieceToBePlaced);
                    stepForwardRow();
                    stepForwardColumn();
                } else {
                    prepareStepBack(slotKey, possiblePiecesForSlot);
                    stepBack();
                }
            }
            if(isSolved){
                System.out.println(String.format("Puzzle solved for board %s X %s",currentBoard.length, currentBoard[0].length));
                break;
            } else {
                System.out.println(String.format("Can't find solution for board %s X %s",currentBoard.length, currentBoard[0].length));
                //TODO send error to aggregator
            }
        }
        if(!isSolved){
            //TODO send error to aggregator
            System.out.println("No solution found for given pieces");
        }
    }

    private void prepareStepBack(String slotKey, List<PuzzlePiece> possiblePiecesForSlot) {
        slotToPieces.remove(slotKey, possiblePiecesForSlot);
        returnPieceToPool();
        currentBoard[row][col] = null;
    }

    private void stepBack() {
        stepBackRow();
        stepBackColumn();
    }

    private void returnPieceToPool() {
        if(currentBoard[row][col] != null){
            poolOfPieces.add(currentBoard[row][col]);
            System.out.println(currentBoard[row][col] + " returned to poolOfPieces");
        }
    }

    private void stepBackColumn() {
        if(col == 0){
            col = currentBoard[0].length - 1;
        } else {
            col--;
        }

    }

    private void stepBackRow() {
        if(col == 0){
            row--;
        }
    }

    private void stepForwardColumn() {
        if(col == currentBoard[0].length - 1){
            col = 0;
        } else {
            col++;
        }
    }

    private void stepForwardRow() {
        if(col == currentBoard[0].length - 1){
            row++;
        }
    }

    private List<PuzzlePiece> piecesForSlot() {
        List<PuzzlePiece> ret = new ArrayList<>();
        int sideLeft, sideTop, sideRight, sideBottom;
        if( row == 0 && col == 0 && currentBoard[row][col] == null){
            sideLeft = 0;
            sideTop = 0;
            sideRight = 2;
            sideBottom = 2;
        } else {
            // todo fake piece object, add compare to PuzzlePiec
            sideLeft = getRightSideFromLeftPiece()*(-1);
            sideTop = getBottomSideFromUpPiece() *(-1);
            sideRight = getLeftSideFromRightPice() ; // should be 0 or 2
            sideBottom = getTopSideFromDownPiece() ; // should be 0 or 2

        }

        for(PuzzlePiece piece: poolOfPieces){
            if(isPieceFit(piece, sideLeft, sideTop, sideRight, sideBottom)){
                ret.add(piece);
            }
        }
        return ret;
    }

    private int getTopSideFromDownPiece() {
        if ( row == currentBoard.length - 1){
            return 0;
        } else {
            return 2;
        }
    }

    private int getLeftSideFromRightPice() {
        if (col == currentBoard[0].length - 1){
            return 0;
        } else {
            return 2;
        }
    }

    private int getBottomSideFromUpPiece() {
        if( row == 0){
            return 0;
        } else {
            return currentBoard[row-1][col].getSideBottom();
        }
    }

    private int getRightSideFromLeftPiece(){
        if( col == 0){
            return 0;
        }
        return currentBoard[row][col - 1].getSideRight();

    }

    // todo fake piece object, add compare to PuzzlePiec
    private boolean isPieceFit(PuzzlePiece piece, int sideLeft, int sideTop, int sideRight, int sideBottom) {
        boolean[] sideStatus = new boolean[4];
        if(sideLeft >= 2){
            sideStatus[0] = true;
        } else {
            sideStatus[0] = sideLeft == piece.getSideLeft();
        }
        if(sideTop >= 2){
            sideStatus[1] = true;
        } else {
            sideStatus[1] = sideTop == piece.getSideTop();
        }
        if(sideRight >= 2){
            sideStatus[2] = true;
        } else {
            sideStatus[2] = sideRight == piece.getSideRight();
        }
        if(sideBottom >= 2){
            sideStatus[3] = true;
        } else {
            sideStatus[3] = sideBottom == piece.getSideBottom();
        }
        return sideStatus[0] && sideStatus[1] && sideStatus[2] && sideStatus[3];
    }

    public boolean validatePuzzleSolution(){
        int sumOfSides = 0;
        // validate rows
        for(int row = 0; row < currentBoard.length; row++){
            for(int col = 0; col < currentBoard[0].length; col++){
                PuzzlePiece currPiece = currentBoard[row][col];
                if(currPiece != null){
                    sumOfSides += currPiece.getSideRight() + currPiece.getSideLeft();
                } else {
                    // TODO error to aggregator
                    return false;
                }
            }
            if(sumOfSides != 0){
                //TODO error to aggregator
                return false;
            }
        }
        // validate columns
        for(int col = 0; col < currentBoard[0].length; col++){
            for(int row = 0; row < currentBoard.length; row++){
                PuzzlePiece currPiece = currentBoard[row][col];
                if(currPiece != null){
                    sumOfSides += currPiece.getSideTop() + currPiece.getSideBottom();
                } else {
                    // TODO error to aggregator
                    return false;
                }
            }
            if(sumOfSides != 0){
                //TODO error to aggregator
                return false;
            }
        }
        return true;
    }

    public List<PuzzlePiece> getPoolOfPieces() {
        return poolOfPieces;
    }

    public PuzzlePiece[][] getCurrentBoard() {
        if (currentBoard == null || currentBoard[0][0] == null){
            return null;
        }
        return currentBoard;
    }
}
