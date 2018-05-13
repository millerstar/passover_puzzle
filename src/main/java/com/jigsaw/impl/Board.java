package com.jigsaw.impl;


import java.util.*;

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
    private List<PuzzlePiece> piecesOnBoard = new ArrayList<>();



    public Board(PuzzlePiece[][] board) {
        this.id = board.length + "X" + board[0].length;
        this.board = board;
        row = 0;
        col = 0;
        rowLength = board.length;
        colLength = board[0].length;
    }

    public List<PuzzlePiece> getPiecesOnBoard() {
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

    // return false if list don't conatain pieces that could be placed on board
    public boolean addPiece(List<PuzzlePiece> piecesToBePlaced) {
        // step back action, remove piece from slot and set slot to primary position
        if (piecesToBePlaced == null){
            piecesOnBoard.remove(currentSlot());
            setCurrenSlot(null);
            return true;
        }
        for(PuzzlePiece piece: piecesToBePlaced){
            if(!piecesOnBoard.contains(piece)){
                if(currentSlot() != null ){
                    piecesOnBoard.remove(currentSlot());
                    System.out.println(String.format(Thread.currentThread().getName() + " Piece %s removed from board", currentSlot()));
                }
                setCurrenSlot(piece);
                piecesOnBoard.add(piece);
                System.out.println(String.format(Thread.currentThread().getName() + " Piece %s added to board", board[row][col]));
                return true;
            }
        }
        return false;
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
