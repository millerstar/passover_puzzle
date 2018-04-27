package com.jigsaw.impl;



/**
 *
 *  Author:
 *  Date:   06/04/2018
 */


public class Board {

    String id;
    private PuzzlePiece[][] board;
    private int row;
    private int col;
    private int rowLength;
    private int colLength;



    public Board(PuzzlePiece[][] board) {
        this.id = board.length + "X" + board[0].length;
        this.board = board;
        row = 0;
        col = 0;
        rowLength = board.length;
        colLength = board[0].length;
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
        board[row][col] = pieceToBePlaced;
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
}
