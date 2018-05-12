package com.jigsaw.impl;

import java.util.Objects;

/**
 *
 *  Author:     Idan Agam
 *  Date:       01/04/2018
 *  Modified:   16/04/2018
 */

//TODO: add c'Tor with and without boolean rotation
public class PuzzlePiece {
    
    private Shape shape;
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
        this.shape = new Shape(sideLeft, sideRight, sideTop, sideBottom);
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
        this(piece.getSideLeft(), piece.getSideRight(), piece.getSideTop(), piece.getSideBottom(), piece.pieceID);
        this.setRotationDegree(piece.getRotationDegree());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getShape(), getPieceID(), getRotationDegree(), isIdPieceOnTheBoard());
    }

    public int getSideLeft() {
        return shape.getSideLeft();
    }

    public void setSideLeft(int sideLeft) {
        this.shape.setSideLeft(sideLeft);
    }

    public int getSideRight() {
        return shape.getSideRight();
    }

    public void setSideRight(int sideRight) {
        this.shape.setSideRight(sideRight);
    }

    public int getSideTop() {
        return shape.getSideTop();
    }

    public void setSideTop(int sideTop) {
        this.shape.setSideTop(sideTop);
    }

    public int getSideBottom() {
        return shape.getSideBottom();
    }

    public void setSideBottom(int sideBottom) {
        this.shape.setSideBottom(sideBottom);
    }

    public int getPieceID() {
        return pieceID;
    }

    public void setPieceID(int pieceID) {
        this.pieceID = pieceID;
    }

    public boolean isSumOfAllSidesZero() {
        //TODO: check when to use

        return this.shape.getSideLeft() == 0 &&
                this.shape.getSideRight() == 0 &&
                this.shape.getSideBottom() == 0 &&
                this.shape.getSideBottom() == 0;
    }

    public int getSumOfAllSides() {
        return this.shape.getSideLeft() + this.shape.getSideRight() + this.shape.getSideTop() + this.shape.getSideBottom();
    }

    public boolean isTopRightCorner() {
        return this.shape.getSideRight() == 0 && this.shape.getSideTop() == 0;
    }

    public boolean isBottomRightCorner() {
        return this.shape.getSideBottom() == 0 && this.shape.getSideRight() == 0;
    }

    public boolean isTopLeftCorner() {
        return this.shape.getSideTop() == 0 && this.shape.getSideLeft() == 0;
    }

    public boolean isBottomLeftCorner() {
        return this.shape.getSideBottom() == 0 && this.shape.getSideLeft() == 0;
    }

    public boolean isPieceACorner() { return isTopRightCorner()||isBottomRightCorner()||isTopLeftCorner()||isBottomLeftCorner(); }

    @Override
    public String toString() {
        return "[#" + this.pieceID + " " + shape + " Rot=" + this.rotationDegree + "]";
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

    public boolean isEqualByAllParams(Object other) {

        if (!(other instanceof PuzzlePiece)) { return false; }

        PuzzlePiece o = (PuzzlePiece) other;
        return (this.equals(other) &&
                this.isEqualByShape(other) &&
                this.rotationDegree == ((PuzzlePiece) other).rotationDegree);
    }

    public boolean isValid(){
        if (shape.getSideLeft()<-1||shape.getSideLeft()>1 ||
            shape.getSideRight()<-1||shape.getSideRight()>1 ||
            shape.getSideBottom()<-1||shape.getSideBottom()>1 ||
            shape.getSideTop()<-1||shape.getSideTop()>1){
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
        this.rotationDegree += 90;
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

    public Shape getShape() {
        return shape;
    }
}



