package com.jigsaw.impl;

public class PuzzlePiece {
	
	//Private class members
	private int TL,TR,BL,BR;
	private int PazzlePieceID = 0;
	
	
	//Ctors
	public PuzzlePiece(int TL, int TR, int BL, int BR) {
		this.TL = TL;
		this.TR = TR;
		this.BL = BL;
		this.BR = BR;
		PazzlePieceID++;
	}

}
