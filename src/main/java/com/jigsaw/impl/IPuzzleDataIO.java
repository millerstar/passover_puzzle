package com.jigsaw.impl;

import java.io.IOException;
import java.util.List;

public interface IPuzzleDataIO {

    List<PuzzlePiece> getPuzzlePieces() throws IOException;

    void printPuzzleResult(Solver solver);
}
