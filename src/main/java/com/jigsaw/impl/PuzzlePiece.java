package com.jigsaw.impl;

public class PuzzlePiece {

	private int topLeft, topRight, bottomLeft, bottomRight;
	private int pieceID;

    public PuzzlePiece(int topLeft, int topRight, int bottomLeft, int bottomRight, int pieceID) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
        this.pieceID = pieceID;
    }

}
