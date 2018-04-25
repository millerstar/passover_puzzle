package com.jigsaw.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SolverTests {

    public PuzzleBox createPuzzleBox (PuzzlePiece... pieces) throws WrongElementsFormat {

        ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>(Arrays.asList(pieces));

        Collections.shuffle(puzzlePieces);
        return new PuzzleBox(puzzlePieces);
    }


    @Test
    public void fourPiecesTest() throws WrongElementsFormat {
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
    public void sixPiecesTest() throws WrongElementsFormat {
        PuzzlePiece p6 = new PuzzlePiece(0, -1, 0, 0, 1);
        PuzzlePiece p3 = new PuzzlePiece(1, -1, 0, 0, 2);
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 3);
        PuzzlePiece p4 = new PuzzlePiece(0, 1, 0, 0, 4);
        PuzzlePiece p2 = new PuzzlePiece(-1, -1, 0, 0, 5);
        PuzzlePiece p5 = new PuzzlePiece(1, 0, -1, 0, 6);

        Solver solver = new Solver(createPuzzleBox(p1, p2, p3, p4, p5, p6));
        solver.createPossibleBoards();
        solver.solvePuzzle();
        assertTrue(solver.validatePuzzleSolution());
    }

    // long run
    @Disabled
    @Test
    public void thirtySixPiecesTest() throws WrongElementsFormat {
        PuzzlePiece p6 = new PuzzlePiece(0, -1, 0, 0, 1);
        PuzzlePiece p3 = new PuzzlePiece(1, -1, 0, 0, 2);
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 3);
        PuzzlePiece p4 = new PuzzlePiece(0, 1, 0, 0, 4);
        PuzzlePiece p2 = new PuzzlePiece(-1, -1, 0, 0, 5);
        PuzzlePiece p5 = new PuzzlePiece(1, 0, -1, 0, 6);

        PuzzlePiece p7 = new PuzzlePiece(0, -1, 0, 0, 11);
        PuzzlePiece p8 = new PuzzlePiece(1, -1, 0, 0, 12);
        PuzzlePiece p9 = new PuzzlePiece(1, 0, 0, 1, 13);
        PuzzlePiece p10 = new PuzzlePiece(0, 1, 0, 0, 14);
        PuzzlePiece p11 = new PuzzlePiece(-1, -1, 0, 0, 15);
        PuzzlePiece p12 = new PuzzlePiece(1, 0, -1, 0, 16);

        PuzzlePiece p13 = new PuzzlePiece(0, -1, 0, 0, 21);
        PuzzlePiece p14 = new PuzzlePiece(1, -1, 0, 0, 22);
        PuzzlePiece p15 = new PuzzlePiece(1, 0, 0, 1, 23);
        PuzzlePiece p16 = new PuzzlePiece(0, 1, 0, 0, 24);
        PuzzlePiece p17 = new PuzzlePiece(-1, -1, 0, 0, 25);
        PuzzlePiece p18 = new PuzzlePiece(1, 0, -1, 0, 26);

        PuzzlePiece p19 = new PuzzlePiece(0, -1, 0, 0, 31);
        PuzzlePiece p20 = new PuzzlePiece(1, -1, 0, 0, 32);
        PuzzlePiece p21 = new PuzzlePiece(1, 0, 0, 1, 33);
        PuzzlePiece p22 = new PuzzlePiece(0, 1, 0, 0, 34);
        PuzzlePiece p23 = new PuzzlePiece(-1, -1, 0, 0, 35);
        PuzzlePiece p24 = new PuzzlePiece(1, 0, -1, 0, 36);

        PuzzlePiece p25 = new PuzzlePiece(0, -1, 0, 0, 41);
        PuzzlePiece p26 = new PuzzlePiece(1, -1, 0, 0, 42);
        PuzzlePiece p27 = new PuzzlePiece(1, 0, 0, 1, 43);
        PuzzlePiece p28 = new PuzzlePiece(0, 1, 0, 0, 44);
        PuzzlePiece p29 = new PuzzlePiece(-1, -1, 0, 0, 45);
        PuzzlePiece p30 = new PuzzlePiece(1, 0, -1, 0, 46);

        PuzzlePiece p31 = new PuzzlePiece(0, -1, 0, 0, 51);
        PuzzlePiece p32 = new PuzzlePiece(1, -1, 0, 0, 52);
        PuzzlePiece p33 = new PuzzlePiece(1, 0, 0, 1, 53);
        PuzzlePiece p34 = new PuzzlePiece(0, 1, 0, 0, 54);
        PuzzlePiece p35 = new PuzzlePiece(-1, -1, 0, 0, 55);
        PuzzlePiece p36 = new PuzzlePiece(1, 0, -1, 0, 56);

        PuzzleBox puzzleBox = createPuzzleBox(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24,p25, p26, p27, p28, p29, p30,p31, p32, p33, p34, p35, p36);


        Solver solver = new Solver(puzzleBox);
        solver.createPossibleBoards();
        solver.solvePuzzle();
        assertTrue(solver.validatePuzzleSolution());
    }

    @Test
    public void negativeFourPiecesTest() throws WrongElementsFormat {
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
    public void positiveValidatePuzzleSolutionTest() throws WrongElementsFormat {
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
    public void negativeValidatePuzzleSolutionTest() throws WrongElementsFormat {
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
    public void posValidateOneRowSolutionTest() throws WrongElementsFormat {
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
    public void posOneLineSolutionsCheckerTest() throws WrongElementsFormat {
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
    public void posOneColumnSolutionTest() throws WrongElementsFormat {
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
    public void negativeOneLineSolution() throws WrongElementsFormat {
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
