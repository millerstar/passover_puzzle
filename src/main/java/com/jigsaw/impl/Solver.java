package com.jigsaw.impl;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 *
 *  Author: Matvey
 *  Date:   01/04/2018
 */

public class Solver{

    private List<Board> boards = new ArrayList<>(); // todo add class for board, add members that save prev and current possition
    private ThreadLocal<Board> currentBoard = new ThreadLocal<>();
    private Board solvedBoard;
    private PuzzleBox puzzleBox;
    private List<PuzzlePiece> poolOfPieces = new ArrayList<>();
    private Map<Shape, ArrayList<PuzzlePiece>> shapeToPieces = new HashMap<>();
    private ThreadLocal<Map<String, List<PuzzlePiece>>> slotToPieces= ThreadLocal.withInitial(HashMap::new);
    private boolean isOneRowSolutionPossible;
    private boolean isOneColumnSolutionPossible;
    public AtomicBoolean isPuzzleSolved = new AtomicBoolean(false);

    // constructor for negative flow, use only isPuzzleSolved member
    public Solver() {
    }

    public boolean isOneRowSolutionPossible() {
        return isOneRowSolutionPossible;
    }

    public boolean isOneColumnSolutionPossible() {
        return isOneColumnSolutionPossible;
    }

    public Solver(PuzzleBox puzzleBox) {
        this.puzzleBox = puzzleBox;
        if(puzzleBox.isRotate()){
            this.poolOfPieces.addAll(this.puzzleBox.getAllPiecesInBoardWithRotation());
        } else {
            this.poolOfPieces.addAll(this.puzzleBox.getAllPiecesInBoard());
        }
        Collections.shuffle(poolOfPieces);
        createShapeToPiecesMap(poolOfPieces);
    }

    private void createShapeToPiecesMap(List<PuzzlePiece> pieces) {
        for(PuzzlePiece piece: pieces){
            ArrayList<PuzzlePiece> currList = shapeToPieces.getOrDefault(piece.getShape(), new ArrayList<>());
            shapeToPieces.put(piece.getShape(), currList);
        }
    }

    public PuzzleBox getPuzzleBox() {
        return puzzleBox;
    }

    public void createPossibleBoards(){
        int numOfPieces = puzzleBox.getAllPiecesInBoard().size();
        oneLineSolutionsChecker();
        for( int i = numOfPieces/2; i >= 2; i--){
            if(numOfPieces % i == 0){
                PuzzlePiece[][] board = new PuzzlePiece[i][numOfPieces/i];
                boards.add(new Board(board));
            }
        }
        if (isOneRowSolutionPossible){
            boards.add(new Board(new PuzzlePiece[1][numOfPieces]));
        }
        if (isOneColumnSolutionPossible){
            boards.add(new Board(new PuzzlePiece[numOfPieces][1]));
        }
    }

    // check is it possible to create one row or one column solutions
    private void oneLineSolutionsChecker() {
        boolean hasPieceWith2RightAngelsOnLeftSide = false;
        boolean hasPieceWith2RightAngelsOnRigthSide = false;
        boolean hasPieceWith2RightAngelsOnTopSide = false;
        boolean hasPieceWith2RightAngelsOnBottomSide = false;

        for(PuzzlePiece piece :puzzleBox.getAllPiecesInBoard()){
            // check for one row
            if(piece.getSideBottom() == 0 && piece.getSideTop() == 0){
                if(piece.getSideLeft() == 0) {
                    hasPieceWith2RightAngelsOnLeftSide = true;
                }
                if (piece.getSideRight() == 0){
                    hasPieceWith2RightAngelsOnRigthSide = true;
                }
            }
            if ( hasPieceWith2RightAngelsOnLeftSide && hasPieceWith2RightAngelsOnRigthSide){
                isOneRowSolutionPossible = true;
            }
            // check for one column
            if(piece.getSideRight() == 0 && piece.getSideLeft() == 0){
                if(piece.getSideTop() == 0) {
                    hasPieceWith2RightAngelsOnTopSide = true;
                }
                if (piece.getSideBottom() == 0){
                    hasPieceWith2RightAngelsOnBottomSide = true;
                }
            }
            if(hasPieceWith2RightAngelsOnBottomSide && hasPieceWith2RightAngelsOnTopSide){
                isOneColumnSolutionPossible = true;
            }
            // exit if 2 one line solutions already possible
            if(isOneColumnSolutionPossible && isOneRowSolutionPossible){
                break;
            }
        }
    }

    private void solveBoard(Board board){
        boolean isSolved = false;
        currentBoard.set(board);
        System.out.println(String.format(Thread.currentThread().getName() + " Starting to check board %s X %s",currentBoard.get().getBoard().length, currentBoard.get().getBoard()[0].length));
        while(true){
            if(isPuzzleSolved.get()){
                break;
            }
            if (currentBoard.get().getRow() < 0){
                break;
            }
            if (currentBoard.get().getRow() == currentBoard.get().getBoard().length){
                isSolved = true;
                break;
            }
            String slotKey = Integer.toString(board.getRow()) + Integer.toString(board.getCol());
            if (!slotToPieces.get().containsKey(slotKey)) {
                slotToPieces.get().put(slotKey, piecesForSlot());
            }

            List<PuzzlePiece> possiblePiecesForSlot = slotToPieces.get().get(slotKey);

            // clean bank of possible pieces from pieces with id that already on board
            for(PuzzlePiece piece: currentBoard.get().getPiecesOnBoard()){
                while(possiblePiecesForSlot.contains(piece)){
                    possiblePiecesForSlot.remove(piece);
                }
            }

            if(possiblePiecesForSlot.isEmpty()){
                prepareStepBack(slotKey, possiblePiecesForSlot);
                stepBack();
                continue;
            }

            if (possiblePiecesForSlot.size() > 0) {
                PuzzlePiece pieceToBePlaced = possiblePiecesForSlot.get(0);
                currentBoard.get().addPiece(pieceToBePlaced);
                if(!possiblePiecesForSlot.remove(pieceToBePlaced)){
                    System.out.println("Warning, piece " + pieceToBePlaced.getPieceID() + "not in possible pieces");
                }
                System.out.println(String.format(Thread.currentThread().getName() + " Piece %s removed from possible pieces", pieceToBePlaced.getPieceID()));
                currentBoard.get().stepForwardRow();
                currentBoard.get().stepForwardColumn();
                System.out.println(String.format(Thread.currentThread().getName() + " Going forward to %d%d", currentBoard.get().getRow(), currentBoard.get().getCol()));
            } else {
                prepareStepBack(slotKey, possiblePiecesForSlot);
                stepBack();
            }
        }
        if(isSolved){
            System.out.println(String.format(Thread.currentThread().getName() + " Puzzle solved for board %s X %s",currentBoard.get().numberOfRows(), currentBoard.get().numberOfColumns()));
            isPuzzleSolved.compareAndSet(false, true);
            solvedBoard = currentBoard.get();
        } else if (isPuzzleSolved.get()) {
            System.out.println(String.format(Thread.currentThread().getName() + " Solution for board already found, giving up on %s X %s",currentBoard.get().numberOfRows(), currentBoard.get().numberOfColumns()));
        } else {
            System.out.println(String.format(Thread.currentThread().getName() + " Can't find solution for board %s X %s",currentBoard.get().numberOfRows(), currentBoard.get().numberOfColumns()));
        }
    }

    public void solvePuzzle(){
        // fixed number of threads solution
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Config.getInstance().getThreadsNumber());
        for(Board board: boards){
            executor.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            solveBoard(board);
                        }
                    }
            );
        }

        executor.shutdown();
        // wait for all threads to stop
        while (!executor.isTerminated()) {
        }

        System.out.println("Finished all threads");
        if(!isPuzzleSolved.get()){
            System.out.println(Thread.currentThread().getName() + " No solution found for given pieces");
            MessageAccumulator.addMassage("Cannot solve puzzle: it seems that there is no proper solution");
        }
        //return isPuzzleSolved.get() && validatePuzzleSolution();
    }

    private void prepareStepBack(String slotKey, List<PuzzlePiece> possiblePiecesForSlot) {
        slotToPieces.get().remove(slotKey, possiblePiecesForSlot);
//        returnPieceToPool();
        currentBoard.get().addPiece(null);
    }

    private void stepBack() {
        currentBoard.get().stepBackRow();
        currentBoard.get().stepBackColumn();
        System.out.println(String.format("Going back to %d%d", currentBoard.get().getRow(), currentBoard.get().getCol()));
    }

//    private void returnPieceToPool() {
//        if(currentBoard.get().currentSlot() != null){
//            poolOfPieces.get().add(currentBoard.get().currentSlot());
//            System.out.println(Thread.currentThread().getName() + " " + currentBoard.get().currentSlot() + " returned to poolOfPieces");
//        }
//    }

    private List<PuzzlePiece> piecesForSlot() {
        List<PuzzlePiece> ret = new ArrayList<>();
        int sideLeft, sideTop, sideRight, sideBottom;
        if( currentBoard.get().getRow() == 0 && currentBoard.get().getCol() == 0 && currentBoard.get().currentSlot() == null){
            sideLeft = 0;
            sideTop = 0;
            // check for one row
            if( currentBoard.get().numberOfColumns() == 1) {
                sideRight = 0;
            } else {
                sideRight = 2;
            }
            // check for one column
            if (currentBoard.get().numberOfRows() == 1){
                sideBottom = 0;
            } else {
                sideBottom = 2;
            }

        } else {
            // todo fake piece object, add compare to PuzzlePiec
            sideLeft = currentBoard.get().getRightSideFromLeftPiece()*(-1);
            sideTop = currentBoard.get().getBottomSideFromUpPiece() *(-1);
            sideRight = currentBoard.get().getLeftSideFromRightPice() ; // should be 0 or 2
            sideBottom = currentBoard.get().getTopSideFromDownPiece() ; // should be 0 or 2

        }

        for(PuzzlePiece piece: poolOfPieces){
            if(isPieceFit(piece, sideLeft, sideTop, sideRight, sideBottom)){
                ret.add(piece);
            }
        }
        return ret;
    }

    // todo fake piece object, add compare to PuzzlePiec
    private boolean isPieceFit(PuzzlePiece piece, int sideLeft, int sideTop, int sideRight, int sideBottom) {
        return  (sideLeft == 2 || sideLeft == piece.getSideLeft()) &&
                (sideTop == 2 || sideTop == piece.getSideTop()) &&
                (sideRight == 2 || sideRight == piece.getSideRight()) &&
                (sideBottom == 2 || sideBottom == piece.getSideBottom());
    }

   // private void removePieceFromList()

    public boolean validatePuzzleSolution(){
        Set<Integer> setOfIds = new HashSet<>();
        if (isPuzzleSolved.get()){
            int sumOfSides = 0;
            // validate rows
            for(int row = 0; row < solvedBoard.numberOfRows(); row++){
                PuzzlePiece prevPiece = null;
                for(int col = 0; col < solvedBoard.numberOfColumns(); col++){
                    PuzzlePiece currPiece = solvedBoard.getBoard()[row][col];
                    if(!setOfIds.add(currPiece.getPieceID())){
                        System.out.println(String.format("Pieces %s more then one time on board, validation failed",currPiece.getPieceID()));
                        return false;
                    }
                    if(currPiece != null){
                        sumOfSides += currPiece.getSideRight() + currPiece.getSideLeft();
                    } else {
                        System.out.println(String.format("Puzzle wasn't solved, slot %d:%d is empty", row, col));
                        MessageAccumulator.addMassage(String.format("Puzzle wasn't solved, slot %d:%d is empty", row, col));
                        return false;
                    }
                    if( prevPiece != null){
                        if ((prevPiece.getSideRight() + currPiece.getSideLeft()) != 0){
                            System.out.println(String.format("Pieces %s right side are not comatible with piece %s left side", prevPiece.getPieceID(), currPiece.getPieceID()));
                            return false;
                        }
                    }
                    prevPiece = currPiece;
                }
                if(sumOfSides != 0){
                    System.out.println(String.format("Puzzle wasn't solved, sum of row %d is not zero", row));
                    MessageAccumulator.addMassage(String.format("Puzzle wasn't solved, sum of row %d is not zero", row));
                    return false;
                }
            }
            // validate columns
            for(int col = 0; col < solvedBoard.numberOfColumns(); col++){
                PuzzlePiece prevPiece = null;
                for(int row = 0; row < solvedBoard.numberOfRows(); row++){
                    PuzzlePiece currPiece = solvedBoard.getBoard()[row][col];
                    if(currPiece != null){
                        sumOfSides += currPiece.getSideTop() + currPiece.getSideBottom();
                    } else {
                        System.out.println(String.format("Puzzle wasn't solved, slot %d:%d is empty", row, col));
                        MessageAccumulator.addMassage(String.format("Puzzle wasn't solved, slot %d:%d is empty", row, col));
                        return false;
                    }
                    if( prevPiece != null){
                        if ((prevPiece.getSideBottom() + currPiece.getSideTop()) != 0){
                            System.out.println(String.format("Pieces %s bottom side are not comatible with piece %s top side", prevPiece.getPieceID(), currPiece.getPieceID()));
                            return false;
                        }
                    }
                    prevPiece = currPiece;
                }
                if(sumOfSides != 0){
                    System.out.println(String.format("Puzzle wasn't solved, sum of column %d is not zero", col));
                    MessageAccumulator.addMassage(String.format("Puzzle wasn't solved, sum of column %d is not zero", col));
                    return false;
                }
            }
            System.out.println(solvedBoard.toString());
            return true;
        }
        System.out.println("Puzzle wasn't salved at all !!!");
        return false;
    }

//    public List<PuzzlePiece> getPoolOfPieces() {
//        return poolOfPieces.get();
//    }

    public PuzzlePiece[][] getCurrentBoard() {
        if (solvedBoard.getBoard() == null || solvedBoard.getBoard()[0][0] == null){
            return null;
        }
        return solvedBoard.getBoard();
    }
}
