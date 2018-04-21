package com.jigsaw.impl;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SolverTests {

    public PuzzleBox createPuzzleBox (PuzzlePiece... pieces){
        return new PuzzleBox(
                new ArrayList<PuzzlePiece>(
                        Arrays.asList(pieces)));
    }

    @Test
    public void fourPiecesTest(){
        PuzzlePiece p1 = new PuzzlePiece(0, 1, 0, 0, 1);
        PuzzlePiece p2 = new PuzzlePiece(-1, 0, 0, 0, 2);
        PuzzlePiece p3 = new PuzzlePiece(0, 1, 0, 0, 3);
        PuzzlePiece p4 = new PuzzlePiece(-1, 0, 0, 0, 4);

        Solver solver = new Solver(createPuzzleBox(p1, p2, p3, p4));
        solver.createPossibleBoards();
        solver.solvePuzzle();
        assertTrue(solver.validatePuzzleSolution());
    }

    @Test
    public void sixPiecesTest(){
        PuzzlePiece p6 = new PuzzlePiece(0, -1, 0, 0, 1);
        PuzzlePiece p3 = new PuzzlePiece(1, -1, 0, 0, 2);
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 3);
        PuzzlePiece p4 = new PuzzlePiece(0, 1, 0, 0, 4);
        PuzzlePiece p2 = new PuzzlePiece(-1, -1, 0, 0, 5);
        PuzzlePiece p5 = new PuzzlePiece(1, 0, -1, 0, 6);

        Solver solver = new Solver(createPuzzleBox(p6, p3, p1, p4, p2, p5));
        solver.createPossibleBoards();
        solver.solvePuzzle();
        assertTrue(solver.validatePuzzleSolution());
    }

    @Test
    public void negativeFourPiecesTest(){
        PuzzlePiece p1 = new PuzzlePiece(0, 1, 0, 0, 1);
        PuzzlePiece p2 = new PuzzlePiece(-1, 0, 0, 0, 2);
        PuzzlePiece p3 = new PuzzlePiece(0, 1, 0, 0, 3);
        PuzzlePiece p4 = new PuzzlePiece(-1, -1, 0, 0, 4);

        Solver solver = new Solver(createPuzzleBox(p1, p2, p3, p4));
        solver.createPossibleBoards();
        solver.solvePuzzle();
        assertTrue(solver.getPoolOfPieces().size() == solver.getPuzzleBox().getAllPiecesInBoard().size());
        assertFalse(solver.validatePuzzleSolution());
    }

    @Test
    public void positiveValidatePuzzleSolutionTest(){
        PuzzlePiece p1 = new PuzzlePiece(0, 1, 0, 0, 1);
        PuzzlePiece p2 = new PuzzlePiece(-1, 0, 0, 0, 2);
        PuzzlePiece p3 = new PuzzlePiece(0, 1, 0, 0, 3);
        PuzzlePiece p4 = new PuzzlePiece(-1, 0, 0, 0, 4);

        Solver solver = new Solver(createPuzzleBox(p1, p2, p3, p4));
        solver.createPossibleBoards();
        solver.solvePuzzle();
        assertTrue(solver.validatePuzzleSolution());
    }

    @Test
    public void negativeValidatePuzzleSolutionTest(){
        PuzzlePiece p1 = new PuzzlePiece(0, 1, 0, 0, 1);
        PuzzlePiece p2 = new PuzzlePiece(-1, 0, 0, 0, 2);
        PuzzlePiece p3 = new PuzzlePiece(0, 1, 0, 0, 3);
        PuzzlePiece p4 = new PuzzlePiece(-1, 0, 0, 0, 4);

        Solver solver = new Solver(createPuzzleBox(p1, p2, p3, p4));
        solver.createPossibleBoards();
        solver.solvePuzzle();
        solver.getCurrentBoard()[0][0] = p4;
        assertFalse(solver.validatePuzzleSolution());
    }

    @Test
    public void posValidateOneRowSolutionTest(){
        PuzzlePiece p1 = new PuzzlePiece(0, 1, 0, 0, 1);
        PuzzlePiece p2 = new PuzzlePiece(-1, 1, 0, 0, 2);
        PuzzlePiece p3 = new PuzzlePiece(-1, -1, 0, 0, 3);
        PuzzlePiece p4 = new PuzzlePiece(1, 0, 0, 0, 4);

        Solver solver = new Solver(createPuzzleBox(p1, p2, p3, p4));
        solver.createPossibleBoards();
        solver.solvePuzzle();
        assertTrue(solver.validatePuzzleSolution());
    }

    @Test
    public void posOneLineSolutionsCheckerTest(){
        PuzzlePiece p1 = new PuzzlePiece(0, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(0, 0, -1, -1, 2);
        PuzzlePiece p3 = new PuzzlePiece(0, 0, 1, 1, 3);
        PuzzlePiece p4 = new PuzzlePiece(0, 0, -1, 0, 4);

        Solver solver = new Solver(createPuzzleBox(p1, p2, p3, p4));
        solver.createPossibleBoards();
        solver.solvePuzzle();
        assertTrue(solver.validatePuzzleSolution());
    }

    @Test
    public void posOneColumnSolutionTest(){
        PuzzlePiece p1 = new PuzzlePiece(0, 0, 0, 0, 1);
        PuzzlePiece p2 = new PuzzlePiece(0, 0, 0, 0, 2);
        PuzzlePiece p3 = new PuzzlePiece(0, 0, 0, 0, 3);
        PuzzlePiece p4 = new PuzzlePiece(0, 0, 0, 0, 4);

        Solver solver = new Solver(createPuzzleBox(p1, p2, p3, p4));
        solver.createPossibleBoards();
        assertTrue(solver.isOneColumnSolutionPossible());
        assertTrue(solver.isOneRowSolutionPossible());
    }

    @Test
    public void negativeOneLineSolution(){
        PuzzlePiece p1 = new PuzzlePiece(0, 0, 0, 0, 1);
        PuzzlePiece p2 = new PuzzlePiece(0, 0, 0, 0, 2);
        PuzzlePiece p3 = new PuzzlePiece(0, 1, 0, 0, 3);
        PuzzlePiece p4 = new PuzzlePiece(0, 0, 0, 0, 4);

        Solver solver = new Solver(createPuzzleBox(p1, p2, p3, p4));
        solver.createPossibleBoards();
        solver.solvePuzzle();
        assertFalse(solver.validatePuzzleSolution());
    }
}
