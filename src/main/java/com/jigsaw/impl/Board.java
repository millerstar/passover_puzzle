package com.jigsaw.impl;



/**
 *
 *  Author:
 *  Date:   06/04/2018
 */


import java.util.ArrayList;
import java.util.List;

public class Board {

    String id;
    private PuzzlePiece[][] board;
    private List<PuzzlePiece> frameSidePieces = new ArrayList<>();
    private List<PuzzlePiece> innerPieces = new ArrayList<>();
    private int[] nextPieceIndex;

    public Board(String id, PuzzlePiece[][] board, int[] nextPieceIndex, List<PuzzlePiece> frameSidePieces, List<PuzzlePiece> innerPieces) {
        this.id = id;
        this.board = board;
        this.nextPieceIndex = nextPieceIndex;
        this.frameSidePieces = frameSidePieces;
        this.innerPieces = innerPieces;
    }

    // TODO copy constructor
    public Board(Board boardToClone){
        this.id = "clonedFrom_" + boardToClone.getId();

        this.board = new PuzzlePiece[boardToClone.board.length][boardToClone.board[0].length];
        for(int i =0; i < this.board.length; i++){
            for(int j=0; j < this.board[0].length; j++){
                this.board[i][j] = boardToClone.board[i][j];
            }
        }

        this.frameSidePieces.addAll(boardToClone.frameSidePieces);
        this.innerPieces.addAll(boardToClone.innerPieces);

        this.nextPieceIndex = new int[boardToClone.nextPieceIndex.length];
        for(int i = 0; i < this.nextPieceIndex.length; i++){
            this.nextPieceIndex[i] = boardToClone.nextPieceIndex[i];
        }
    }

    public Board(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public PuzzlePiece[][] getBoard() {
        return board;
    }

    public int[] getNextPieceIndex() {
        return nextPieceIndex;
    }

    public List<PuzzlePiece> getFrameSidePieces() {
        return frameSidePieces;
    }

    public List<PuzzlePiece> getInnerPieces() {
        return innerPieces;
    }

    public void setFrameSidePieces(List<PuzzlePiece> frameSidePieces) {
        this.frameSidePieces = frameSidePieces;
    }

    public void setInnerPieces(List<PuzzlePiece> innerPieces) {
        this.innerPieces = innerPieces;
    }

    public void setBoard(PuzzlePiece[][] board) {
        this.board = board;
    }

    public void setNextPieceIndex(int[] nextPieceIndex) {
        this.nextPieceIndex = nextPieceIndex;
    }
}
