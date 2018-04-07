package com.jigsaw.impl;

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
    public Board(Board board){
        this.id = "clonedFrom_" + board.getId();
        this.board = board.board; // TODO
        this.nextPieceIndex = board.nextPieceIndex; // TODO
        this.frameSidePieces = board.frameSidePieces; // TODO
        this.innerPieces = board.innerPieces; //TODO
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
