package com.jigsaw.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 *  Author: Idan Agam
 *  Date:   01/04/2018
 */

public class PuzzleBoxTest {


    //Private class members
    private PuzzlePiece squarePiece = new PuzzlePiece(0,0,0,0,1);
    private PuzzlePiece piece1 = new PuzzlePiece(-1,-1,-1,-1,2);
    private PuzzlePiece piece2 = new PuzzlePiece(1,1,1,1,3);
    private PuzzlePiece piece3 = new PuzzlePiece(-1,0,0,-1,4);
    private PuzzlePiece nonValidPiece = new PuzzlePiece(2,0000,100,-80,-1);

    List<PuzzlePiece> puzzle = new ArrayList<>();


    @Test
    @DisplayName("Test constructor")
    void initialzationTest() throws WrongElementsFormat {
        List<PuzzlePiece> puzzle = new ArrayList<>();
        List<PuzzlePiece> puzzleToCompare = new ArrayList<>();

        puzzle.add(piece1);
        PuzzleBox puzzleBox1 = new PuzzleBox(puzzle);
        puzzleToCompare = puzzleBox1.getAllPiecesInBoard();

        PuzzleBox puzzleBoxToCompare = new PuzzleBox(puzzleToCompare);

        assertTrue(puzzleBox1.equals(puzzleBoxToCompare));
    }



//        public PuzzleBox(List<PuzzlePiece> pieces) {
//
//        public int getNumOfPieces() {
//        }
//
//        public List<PuzzlePiece> getMalePiecesGroup(){
//
//
//        public List<PuzzlePiece> getFemalePiecesGroup(){
//
//
//        public List<PuzzlePiece> getStraightPiecesGroup(){
//
//
//        public boolean isSumOfAllSidesZero() {
//
//
//        public List<PuzzlePiece> getAllPiecesInBoard() {
//
//    }
//
//

}
