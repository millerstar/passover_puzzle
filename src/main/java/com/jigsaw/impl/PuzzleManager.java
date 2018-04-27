package com.jigsaw.impl;

import java.io.IOException;

public class PuzzleManager {

    public void findSolutionToPuzzle(String fileFullPath) throws IOException {
        FileManager fileManager = new FileManager(fileFullPath);
//        FileManager fileManager = new FileManager();
        PuzzleBox puzzleBox = null;
        try {
            puzzleBox = new PuzzleBox(fileManager.getPuzzlePieces());
            Solver solver = new Solver(puzzleBox);
            solver.createPossibleBoards();
            solver.solvePuzzle();
            fileManager.printPuzzleResult(solver);
        } catch (WrongElementsFormat wrongElementsFormat) {
            Solver solver = new Solver();
            fileManager.printPuzzleResult(solver);
        }
    }

    public static void main(String[] args) throws IOException {
        PuzzleManager puzzleManager = new PuzzleManager();
        puzzleManager.findSolutionToPuzzle("C:\\Users\\md9897\\Documents\\PuzzlTests\\AdditionalPuzzleTests\\Tests\\test16.in");
    }
}
