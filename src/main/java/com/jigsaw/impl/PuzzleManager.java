package com.jigsaw.impl;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.IOException;

/*
*   Author:  Matvey
 *  Date:   06/04/2018
 */
public class PuzzleManager {

    @Parameter(names = { "-threads"}, description = "Number of threads, default 4")
    private Integer threads = 4;

    @Parameter(names = "-input", description = "Mandatory - position of the puzzle file to solve, default in resources puzzlePiecesFile.txt")
    private String input = "src\\main\\resources\\puzzlePiecesFile.txt";

    @Parameter(names = "-output", description = "Mandatory - position of the puzzle output file (for solution or errors), default in resources puzzleResultFile.txt")
    private String output = "src\\main\\resources\\puzzleResultFile.txt";

    @Parameter(names = "-rotate", description = "Optional - indicating whether the puzzle pieces can be rotated, default false", arity = 1)
    private boolean rotate = false;

    public void findSolutionToPuzzle(String fileFullPath) throws IOException {
        FileManager fileManager = new FileManager(fileFullPath);
        PuzzleBox puzzleBox = null;
        try {
            puzzleBox = new PuzzleBox(fileManager.getPuzzlePieces());
            Solver solver = new Solver(puzzleBox);
            // todo move create to solvePuzzle
            solver.createPossibleBoards();
            // todo return boolean to printPuzzleResult, add validation and check that puzzle solved
            solver.solvePuzzle();
//            if(solver.solvePuzzle()) {
//                fileManager.printPuzzleResult(solver.getCurrentBoard());
//            }
//            else {
//                fileManager.printError();
//            }
            fileManager.printPuzzleResult(solver);
        } catch (WrongElementsFormat wrongElementsFormat) {
            Solver solver = new Solver();
            fileManager.printPuzzleResult(solver);
        }
    }

    public static void main(String[] args) throws IOException {
        PuzzleManager puzzleManager = new PuzzleManager();

        // parse command line params
        JCommander.newBuilder()
                .addObject(puzzleManager)
                .build()
                .parse(args);
        Config.setConfig(puzzleManager.input, puzzleManager.output, puzzleManager.rotate, puzzleManager.threads);

        puzzleManager.findSolutionToPuzzle(Config.getInstance().getInputFilePosition());
    }
}
