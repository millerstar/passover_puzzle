package com.jigsaw.impl;

import java.io.IOException;

public class PuzzleManager {

    public void findSolutionToPuzzle() throws IOException {
        FileManager fileManager = new FileManager();
        PuzzleBox puzzleBox = new PuzzleBox(fileManager.getPuzzlePieces());
        Solver solver = new Solver(puzzleBox);
        solver.createPossibleBoards();
        solver.solvePuzzle();
        fileManager.printPuzzleResult(solver);
    }

    public static void main(String[] args) throws IOException {
        PuzzleManager puzzleManager = new PuzzleManager();
        puzzleManager.findSolutionToPuzzle();
    }
}
