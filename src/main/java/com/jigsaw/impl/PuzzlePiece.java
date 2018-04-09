package com.jigsaw.impl;

public class PuzzlePiece {

	private int sideLeft, sideRight, sideTop, sideBottom;
	private int[] pieceCoordinates;
	private int pieceID;

    public PuzzlePiece(int sideLeft, int sideRight, int sideTop, int sideBottom, int pieceID) {
        this.sideLeft = sideLeft;
        this.sideRight = sideRight;
        this.sideTop = sideTop;
        this.sideBottom = sideBottom;
        this.pieceID = pieceID;
    }

    public int getSideLeft() {
        return sideLeft;
    }

    public void setSideLeft(int sideLeft) {
        this.sideLeft = sideLeft;
    }

    public int getSideRight() {
        return sideRight;
    }

    public void setSideRight(int sideRight) {
        this.sideRight = sideRight;
    }

    public int getSideTop() {
        return sideTop;
    }

    public void setSideTop(int sideTop) {
        this.sideTop = sideTop;
    }

    public int getSideBottom() {
        return sideBottom;
    }

    public void setSideBottom(int sideBottom) {
        this.sideBottom = sideBottom;
    }

    public int getPieceID() {
        return pieceID;
    }

    public void setPieceID(int pieceID) {
        this.pieceID = pieceID;
    }

    public int[] getPieceCoordinates() {
        return pieceCoordinates;
    }

    @Override
    public String toString(){
        return "ID = " +  this.pieceID + ", Sides = " + this.sideLeft + " " + this.sideRight + " " + this.sideTop + " " + this.sideBottom;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof PuzzlePiece)) {
            return false;
        }
        PuzzlePiece o = (PuzzlePiece) other;
        return (o.pieceID==pieceID);
    }
}
