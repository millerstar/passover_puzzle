package com.jigsaw.impl;

public class PuzzlePiece {

	private int sideLeft, sideRight, sideBottom, sideTop;
	private boolean edgeTopLeftIs90, edgeTopRight90, edgeBottomLeftIs90, edgeBottomRight90;
	private int pieceID;

    public PuzzlePiece(int sideLeft, int sideRight, int sideBottom, int sideTop, int pieceID) {
        this.sideLeft = sideLeft;
        this.sideRight = sideRight;
        this.sideBottom = sideBottom;
        this.sideTop = sideTop;
        this.pieceID = pieceID;
    }
}
