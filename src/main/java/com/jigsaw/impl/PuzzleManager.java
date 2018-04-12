package com.jigsaw.impl;

import java.io.IOException;

public class PuzzleManager {

    public void findSolutionToPuzzle() throws IOException {
        PuzzleBox puzzleBox = new PuzzleBox(PuzzleImportPieces.getPuzzlePieces());
        Solver solver = new Solver(puzzleBox);
        solver.createPossibleBoards();
        solver.solvePuzzle();
        FileManager fileManager = new FileManager();
        fileManager.printPuzzleResult(solver.getCurrentBoard());
    }

    public static void main(String[] args) throws IOException {
        PuzzleManager puzzleManager = new PuzzleManager();
        puzzleManager.findSolutionToPuzzle();
    }
}
