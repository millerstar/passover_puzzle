package com.jigsaw.impl;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 *  Author:     Idan Agam
 *  Date:       01/04/2018
 *  Modified:   16/04/2018
 */

//TODO: add c'Tor with and without boolean rotation
public class PuzzlePiece {

    private int sideLeft, sideRight, sideTop, sideBottom;
    private int pieceID;
    private int rotationDegree;

    public boolean isIdPieceOnTheBoard() {
        return idPieceOnTheBoard;
    }

    public void setIdPieceOnTheBoard(boolean idPieceOnTheBoard) {
        this.idPieceOnTheBoard = idPieceOnTheBoard;
    }

    private boolean idPieceOnTheBoard;
//TODO: is piece on the board and where - return coordinates
//Create a piece with default rotation = 0
    public PuzzlePiece(int sideLeft, int sideRight, int sideTop, int sideBottom, int pieceID) {
        this.sideLeft = sideLeft;
        this.sideRight = sideRight;
        this.sideTop = sideTop;
        this.sideBottom = sideBottom;
        this.pieceID = pieceID;
        rotationDegree=0;
    }

    public int getRotationDegree() {
        return rotationDegree;
    }

    public void setRotationDegree(int rotationDegree) {
        this.rotationDegree = rotationDegree;
    }

    //Copy C'tor
    public PuzzlePiece (PuzzlePiece piece){
        this.sideTop = piece.sideTop;
        this.sideBottom = piece.sideBottom;
        this.sideRight = piece.sideRight;
        this.sideLeft = piece.sideLeft;
        this.pieceID = piece.pieceID;
        this.rotationDegree = piece.rotationDegree;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(sideLeft, sideRight, sideTop, sideBottom);
        return 31 * result + Arrays.hashCode(new int[]{pieceID});
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

    public boolean isSumOfAllSidesZero() {
        //TODO: check when to use

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
        return this.sideBottom == 0 && this.sideRight == 0;
    }

    public boolean isTopLeftCorner() {
        return this.sideTop == 0 && this.sideLeft == 0;
    }

    public boolean isBottomLeftCorner() {
        return this.sideBottom == 0 && this.sideLeft == 0;
    }

    public boolean isPieceACorner() { return isTopRightCorner()||isBottomRightCorner()||isTopLeftCorner()||isBottomLeftCorner(); }

    @Override
    public String toString() {
        return "[#" + this.pieceID + " " + "L=" + this.sideLeft + " R=" + this.sideRight + " T=" + this.sideTop + " B=" + this.sideBottom + " Rot=" + this.rotationDegree + "]";
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

            PuzzlePiece o = (PuzzlePiece) other;
            return (this.getSideLeft() == ((PuzzlePiece) other).getSideLeft() &&
                    this.getSideRight() == ((PuzzlePiece) other).getSideRight() &&
                    this.getSideTop() == ((PuzzlePiece) other).getSideTop()) &&
                    this.getSideBottom() == ((PuzzlePiece) other).getSideBottom();
    }

    public boolean isValid(){
        if (sideLeft<-1||sideLeft>1||sideRight<-1||sideRight>1||sideBottom<-1||sideBottom>1||sideTop<-1||sideTop>1){
            return false;
        }
        if (getSumOfAllSides()>4||getSumOfAllSides()<-4){
            return false;
        }

        return true;
    }


    private void rotateRightBy90Degrees(){
        int sideTop=this.getSideTop();
        int sideBottom = this.getSideBottom();
        int sideLeft = this.getSideLeft();
        int sideRight = this.getSideRight();

        this.setSideTop(sideLeft);
        this.setSideRight(sideTop);
        this.setSideBottom(sideRight);
        this.setSideLeft(sideBottom);
    }

    //Returns new rotated puzzle piece only if needed one
   public PuzzlePiece getNewRotetedPuzzlePiece(){
        PuzzlePiece retVal=null;
        if (getPieceOptionalRotations(this)==0){
            return this;
        }
        else{
            retVal = duplicatePuzzlePiece(this);
            retVal.rotateRightBy90Degrees();
        }
        return retVal;
   }

    private PuzzlePiece duplicatePuzzlePiece(PuzzlePiece pieceToDuplicate){
        if (getPieceOptionalRotations(pieceToDuplicate)>0) {
            return new PuzzlePiece(pieceToDuplicate);
        }
        return pieceToDuplicate;
    }

    public int getPieceOptionalRotations(PuzzlePiece piece){
        if (areAllSidesEqual(piece)){
            return 0;
        }
        if (areTwoSidesEqual(piece)){
            return 1;
        }
        return 3;
    }

    private boolean areAllSidesEqual(PuzzlePiece piece){
        return  piece.getSideBottom()==piece.getSideTop() && piece.getSideRight()==piece.getSideLeft()&& piece.getSideRight()==piece.getSideBottom();
    }

    private boolean areTwoSidesEqual(PuzzlePiece piece){
        return (piece.getSideBottom())==(piece.getSideTop()) && ((piece.getSideRight())==(piece.getSideLeft()));
    }
}



