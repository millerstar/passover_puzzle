package com.jigsaw.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 *  Author:
 *  Date:   01/04/2018
 */

public class Solver {

    private List<PuzzlePiece[][]> boards = new ArrayList<>();
    private PuzzlePiece[][] currentBoard;
    private PuzzleBox puzzleBox;
    private List<PuzzlePiece> poolOfPieces = new ArrayList<>();
    private Map<String, List<PuzzlePiece>> slotToPieces= new HashMap<>();

    public Solver(PuzzleBox puzzleBox) {
        this.puzzleBox = puzzleBox;
        this.poolOfPieces.addAll(puzzleBox.getAllPiecesInBoard());
    }

    public void createPossibleBoards(){
        int numOfPieces = puzzleBox.getAllPiecesInBoard().size();
        for( int i = numOfPieces/2; i >= 2; i--){
            if(numOfPieces % i == 0){
                PuzzlePiece[][] board = new PuzzlePiece[i][numOfPieces/i];
                boards.add(board);
            }
        }
    }

    public void solvePuzzle(){
        boolean isSolved = false;
        for(PuzzlePiece[][] board: boards){
            int row = 0;
            int col = 0;
            currentBoard = board;
            while(true){
                if (row < 0){
                    boards.remove(board);
                    break;
                }
                if (row == currentBoard.length){
                    isSolved = true;
                    break;
                }
                String slotKey = Integer.toString(row) + Integer.toString(col);
                if (!slotToPieces.containsKey(slotKey)) {
                    slotToPieces.put(slotKey, piecesForSlot(row, col));
                }

                List<PuzzlePiece> possiblePiecesForSlot = slotToPieces.get(slotKey);
                // remove piece from possible solution to prevent same piece twice on board
                while(!poolOfPieces.contains(possiblePiecesForSlot.get(0))){
                    possiblePiecesForSlot.remove(possiblePiecesForSlot.get(0));
                }

                if (possiblePiecesForSlot.size() > 0) {
                    PuzzlePiece pieceToBePlaced = possiblePiecesForSlot.get(0);
                    if(currentBoard[row][col] != null){
                        poolOfPieces.add(currentBoard[row][col]);
                        System.out.println(currentBoard[row][col] + " removed");
                    }
                    currentBoard[row][col] = pieceToBePlaced;
                    System.out.println(pieceToBePlaced + " added");
                    poolOfPieces.remove(pieceToBePlaced);
                    possiblePiecesForSlot.remove(pieceToBePlaced);
                    row = stepForwardRow(row, col);
                    col = stepForwardColumn(row, col);
                } else {
                    slotToPieces.remove(slotKey, possiblePiecesForSlot);
                    currentBoard[row][col] = null;
                    row = stepBackRow(row, col);
                    col = stepBackColumn(row, col);
                }
            }
            if(isSolved){
                System.out.println("Puzzle solved : " + currentBoard.toString());
            }
        }
    }

    private int stepBackColumn(int row, int col) {
        if(col - 1 < 0){
            return currentBoard.length - 1;
        }
        return col -1;
    }

    private int stepBackRow(int row, int col) {
        if(col - 1 < 0){
            return row - 1;
        }
        return row;
    }

    private int stepForwardColumn(int row, int col) {
        if(col + 1 == currentBoard[0].length){
            return 0;
        }
        return col + 1;
    }

    private int stepForwardRow(int row, int col) {
        if(col + 1 == currentBoard[0].length){
            return row + 1;
        }
        return row;
    }

    private List<PuzzlePiece> piecesForSlot(int row, int col) {
        List<PuzzlePiece> ret = new ArrayList<>();
        int sideLeft, sideTop, sideRight, sideBottom;
        if( row == 0 && col == 0 && currentBoard[row][col] == null){
            sideLeft = 0;
            sideTop = 0;
            sideRight = 2;
            sideBottom = 2;
        } else {
            int backRow = stepBackRow(row, col);
            int backColumn = stepBackColumn(row, col);
            PuzzlePiece oneStepBackPiece = currentBoard[backRow][backColumn];
            sideLeft = oneStepBackPiece.getSideRight()*(-1);
//            sideTop = oneStepBackPiece.getSideTop();
//            sideRight = oneStepBackPiece.getSideRight();
//            sideBottom = oneStepBackPiece.getSideBottom() ;
            sideTop = 2;
            sideRight = 2;
            sideBottom = 2 ;

        }

        for(PuzzlePiece piece: poolOfPieces){
            if(isPieceFit(piece, sideLeft, sideTop, sideRight, sideBottom)){
                ret.add(piece);
            }
        }
        return ret;
    }


    private boolean isPieceFit(PuzzlePiece piece, int sideLeft, int sideTop, int sideRight, int sideBottom) {
        boolean[] sideStatus = new boolean[4];
        if(sideLeft >= 2){
            sideStatus[0] = true;
        } else {
            sideStatus[0] = sideLeft == piece.getSideLeft();
        }
        if(sideTop >= 2){
            sideStatus[1] = true;
        } else {
            sideStatus[1] = sideTop == piece.getSideTop();
        }
        if(sideRight >= 2){
            sideStatus[2] = true;
        } else {
            sideStatus[2] = sideRight == piece.getSideRight();
        }
        if(sideBottom >= 2){
            sideStatus[3] = true;
        } else {
            sideStatus[3] = sideBottom == piece.getSideBottom();
        }
        return sideStatus[0] && sideStatus[1] && sideStatus[2] && sideStatus[3];
    }

    public List<PuzzlePiece> getPoolOfPieces() {
        return poolOfPieces;
    }

    public static void main(String[] args) {
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
    }


}
