package src.main.java.com.jigsaw;

public class PazzlePiece {
	
	//Private class members
	private int TL,TR,BL,BR;
	private static int PazzlePieceID = 0;
	
	
	//Ctors
	public PazzlePiece (int TL,int TR,int BL,int BR) {
		this.TL = TL;
		this.TR = TR;
		this.BL = BL;
		this.BR = BR;
		PazzlePieceID++;
	}

}
