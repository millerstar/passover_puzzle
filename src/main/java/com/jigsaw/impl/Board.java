package com.jigsaw.impl;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *  Author:  Matvey
 *  Date:   06/04/2018
 */


public class Board {

    String id;
    private PuzzlePiece[][] board;
    private int row;
    private int col;
    private int rowLength;
    private int colLength;
    private Set<PuzzlePiece> piecesOnBoard = new HashSet<>();



    public Board(PuzzlePiece[][] board) {
        this.id = board.length + "X" + board[0].length;
        this.board = board;
        row = 0;
        col = 0;
        rowLength = board.length;
        colLength = board[0].length;
    }

    public Set<PuzzlePiece> getPiecesOnBoard() {
        return piecesOnBoard;
    }
    public PuzzlePiece[][] getBoard() {
        return board;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void addPiece(PuzzlePiece pieceToBePlaced) {
        if(currentSlot() != null ){
            piecesOnBoard.remove(currentSlot());
            System.out.println(String.format(Thread.currentThread().getName() + " Piece %s removed from board", currentSlot().getPieceID()));
        }
        setCurrenSlot(pieceToBePlaced);
        // could be null if stepping back
        if( currentSlot() != null){
            piecesOnBoard.add(pieceToBePlaced);
            System.out.println(String.format(Thread.currentThread().getName() + " Piece %s added to board", board[row][col].getPieceID()));
        }
    }

    public int numberOfRows() {
        return rowLength;
    }

    public int numberOfColumns() {
        return colLength;
    }

    public PuzzlePiece currentSlot() {
        return board[row][col];
    }

    private void setCurrenSlot(PuzzlePiece puzzlePiece){
        board[row][col] = puzzlePiece;
    }

    public void stepBackColumn() {
        if(col == 0){
            col = numberOfColumns() - 1;
        } else {
            col--;
        }
    }

    public void stepBackRow() {
        if(col == 0){
            row--;
        }
    }

    public void stepForwardColumn() {
        if(col == numberOfColumns() - 1){
            col = 0;
        } else {
            col++;
        }
    }

    public void stepForwardRow() {
        if(col == numberOfColumns() - 1){
            row++;
        }
    }

    public int getTopSideFromDownPiece() {
        if ( getRow() == numberOfRows() - 1){
            return 0;
        } else {
            return 2;
        }
    }

    public int getLeftSideFromRightPice() {
        if (getCol() == numberOfColumns() - 1){
            return 0;
        } else {
            return 2;
        }
    }

    public int getBottomSideFromUpPiece() {
        if( getRow() == 0){
            return 0;
        } else {
            return board[row-1][col].getSideBottom();
        }
    }

    public int getRightSideFromLeftPiece(){
        if( col == 0){
            return 0;
        }
        return board[getRow()][getCol() - 1].getSideRight();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for(int row = 0; row < numberOfRows(); row++){
            PuzzlePiece prevPiece = null;
            for(int col = 0; col < numberOfColumns(); col++){
                PuzzlePiece currPiece = board[row][col];
                if(currPiece.getRotationDegree() != 0){
                    ret.append("[" +currPiece.getPieceID() + "]");
                } else {
                    ret.append(currPiece.getPieceID());
                }
                ret.append(" ");
            }
            ret.append("\n");
        }
        return ret.toString();
    }
}
