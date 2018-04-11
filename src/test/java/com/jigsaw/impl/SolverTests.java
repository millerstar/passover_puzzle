package com.jigsaw.impl;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SolverTests {

    @Test
    public void fourPiecesTest(){
        PuzzlePiece p1 = new PuzzlePiece(0, 1, 0, 0, 1);
        PuzzlePiece p2 = new PuzzlePiece(-1, 0, 0, 0, 2);
        PuzzlePiece p3 = new PuzzlePiece(0, 1, 0, 0, 3);
        PuzzlePiece p4 = new PuzzlePiece(-1, 0, 0, 0, 4);
        List<PuzzlePiece> pieces = new ArrayList<>();
        pieces.add(p1);
        pieces.add(p2);
        pieces.add(p3);
        pieces.add(p4);
        PuzzleBox puzzleBox = new PuzzleBox(pieces);
        Solver solver = new Solver(puzzleBox);
        solver.createPossibleBoards();
        solver.solvePuzzle();
        assertTrue(solver.getPoolOfPieces().isEmpty());
    }


}
