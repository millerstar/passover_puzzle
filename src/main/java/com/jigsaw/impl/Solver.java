package com.jigsaw.impl;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private List<Board> boards = new ArrayList<>();
    private PuzzleBox puzzleBox;

    public Solver(PuzzleBox puzzleBox) {
        this.puzzleBox = puzzleBox;
    }

    // TODO shold create all kind of boards
    private List<Board> createBasicBoards(){
        List<PuzzlePiece> frameCornerPieces = puzzleBox.getFrameCornerPieces();
        List<Board> newBoards = new ArrayList<>();
        if(frameCornerPieces.size() == 2){
            Board board = createOneLineBoard(frameCornerPieces);
            if (board != null) {
                newBoards.add(board);
            }
            // TODO exeption ?
        }
        return newBoards;
    }

    private Board createOneLineBoard(List<PuzzlePiece> frameCornerPieces) {
        PuzzlePiece firstPiece = frameCornerPieces.get(0);
        PuzzlePiece secondPiece = frameCornerPieces.get(1);

        boolean isOneColumnBoard = firstPiece.getSideLeft() == 0 &&
                                   firstPiece.getSideRight() == 0 &&
                                   secondPiece.getSideLeft() == 0 &&
                                   secondPiece.getSideRight() == 0 ;
        if(isOneColumnBoard){
            PuzzlePiece[][] board = new PuzzlePiece[1][puzzleBox.getNumOfPieces()];
            if(firstPiece.getSideTop() == 0 && secondPiece.getSideBottom() ==0 ){
                board[0][0] = firstPiece;
                board[0][puzzleBox.getNumOfPieces() - 1] = secondPiece;
            } else if ( secondPiece.getSideTop() == 0 && firstPiece.getSideBottom() == 0){
                board[0][0] = secondPiece;
                board[0][puzzleBox.getNumOfPieces() - 1] = firstPiece;
            } else {
                // TODO add exeption, wrong corner pieces
            }
            return new Board("oneColumnBoard", board, new int[]{0,1}, puzzleBox.getFrameSidePieces(), puzzleBox.getInnerPieces());
        }

        boolean isOneRowBoard = firstPiece.getSideTop() == 0 &&
                                firstPiece.getSideBottom() == 0 &&
                                secondPiece.getSideTop() == 0 &&
                                secondPiece.getSideBottom() == 0;
        if(isOneRowBoard){
            PuzzlePiece[][] board = new PuzzlePiece[puzzleBox.getNumOfPieces()][1];
            if(firstPiece.getSideLeft() == 0 && secondPiece.getSideRight() == 0){
                board[0][0] = firstPiece;
                board[puzzleBox.getNumOfPieces()-1][0] = secondPiece;

            } else if ( firstPiece.getSideRight() == 0 && secondPiece.getSideLeft() == 0){
                board[0][0] = secondPiece;
                board[puzzleBox.getNumOfPieces()-1][0] = firstPiece;
            } else {
                // TODO add exeption, wrong corner pieces
            }
            return new Board("oneRowBoard", board, new int[]{1,0}, puzzleBox.getFrameSidePieces(), puzzleBox.getInnerPieces());
        }
        // TODO add exeption, wrong corner pieces for one line puzzle
        return null;
    }

    public void solvePuzzle(){
        while(boards.size() != 0){
            List<Board> boardsToRemove = new ArrayList<>();
            List<Board> boardsToAdd = new ArrayList<>();
            for(Board board: boards){
                PuzzlePiece[][] currentBoard = board.getBoard();
                int row = board.getNextPieceIndex()[0];
                int col = board.getNextPieceIndex()[1];
                PuzzlePiece partialPiece = findComplementarySides(currentBoard, row, col);
                List<PuzzlePiece> matchPieces = findMatchPieces(partialPiece, board);
                if(matchPieces.size() == 0){
                    boardsToRemove.add(board);
                } else if (matchPieces.size() == 1) {
                    currentBoard[row][col] = matchPieces.get(0);
                    board.getFrameSidePieces().remove(matchPieces.get(0));
                    board.getInnerPieces().remove(matchPieces.get(0));
                    board.setNextPieceIndex(findNewNextIndex(row, col, currentBoard));
                } else {
                    // TODO bord duplication in case of number of pieces in same place
                }

            }
        }
    }

    //TODO
    private int[] findNewNextIndex(int row, int col, PuzzlePiece[][] currentBoard) {
        return null;
    }

    // TODO
    private List<PuzzlePiece> findMatchPieces(PuzzlePiece partialPiece, Board board) {
        return null;
    }

    //TODO
    private PuzzlePiece findComplementarySides(PuzzlePiece[][] currentBoard, int row, int col) {
        return null;
    }


}
