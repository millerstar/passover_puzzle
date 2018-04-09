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

    public boolean isSumOfAllSidesZero() {
        //TODO check when to use

        return this.sideLeft == 0 &&
                this.sideRight == 0 &&
                this.sideTop == 0 &&
                this.sideBottom == 0;
    }

    public int getSumOfAllSides() {
        return this.sideLeft + this.sideRight + this.sideTop + this.sideBottom;
    }

    public boolean isTopRightCorner() {
        return this.sideRight == 0 && this.sideTop == 0;
    }

    public boolean isBottomRightCorner() {
        return this.sideRight == 0 && this.sideTop == 0;
    }

    @Override
    public String toString() {
        return "ID = " + this.pieceID + ", Sides = " + this.sideLeft + " " + this.sideRight + " " + this.sideTop + " " + this.sideBottom;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PuzzlePiece)) {
            return false;
        }
        PuzzlePiece o = (PuzzlePiece) other;
        return (o.pieceID == pieceID);
    }

    public boolean isEqualByShape(Object other) {

        if (!(other instanceof PuzzlePiece)) { return false; }
        if (this.pieceID == ((PuzzlePiece) other).pieceID) {return true; }

            PuzzlePiece o = (PuzzlePiece) other;
            return (o.getSideLeft() == ((PuzzlePiece) other).getSideLeft() &&
                    o.getSideRight() == ((PuzzlePiece) other).getSideRight() &&
                    o.getSideTop() == ((PuzzlePiece) other).getSideTop()) &&
                    o.getSideBottom() == ((PuzzlePiece) other).getSideBottom();
        }
    }


