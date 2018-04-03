package com.jigsaw.impl;

import java.util.ArrayList;
import java.util.List;

// this class contain pieces sorted by place they should take
public class PuzzleBox {
    private List<PuzzlePiece> frameCornerPieces = new ArrayList<>() ;
    private List<PuzzlePiece> frameSidePieces = new ArrayList<>();
    private List<PuzzlePiece> innerPieces = new ArrayList<>();

    public PuzzleBox(List<PuzzlePiece> pieces){
        for(PuzzlePiece piece : pieces){
            if(piece.getSideLeft() == 0 || piece.getSideRight() == 0){
                if(piece.getSideTop() == 0 || piece.getSideBottom() == 0){
                    frameCornerPieces.add(piece);
                }
                frameSidePieces.add(piece);
                continue;
            }

            if(piece.getSideTop() == 0 || piece.getSideBottom() == 0){
                if( piece.getSideLeft() == 0 || piece.getSideRight() == 0){
                    frameCornerPieces.add(piece);
                }
                frameSidePieces.add(piece);
                continue;
            }

            innerPieces.add(piece);
        }
    }

}
