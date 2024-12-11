package tictactoe;

import java.util.ArrayList;
import java.util.List;

public class TwoPlayerBoard extends Board{

    int[] rowCount;
    int[] colCount;
    int diagCount;
    int revDiagCount;

    public TwoPlayerBoard(int size) {
        super(size);
        System.out.println("Welcome to Two player tic tac toe... ");
        rowCount = new int[size];
        colCount = new int[size];
        diagCount = 0;
        revDiagCount = 0;
    }

    public boolean makeMove(int row, int col, PlayingPiece playingPiece) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IllegalArgumentException("Out of Bounds");
        }
        if (board[row][col] != null) {
            throw new IllegalArgumentException("Incorrect placement");
        }
        board[row][col] = playingPiece;
        rowCount[row] += playingPiece.getValue();
        colCount[col] += playingPiece.getValue();
        if (row == col) diagCount += playingPiece.getValue();
        if (row == size - col - 1) revDiagCount += playingPiece.getValue();
        return isWinner(row, col);
    }

    protected boolean isWinner(int row, int col) {
        return Math.abs(rowCount[row]) == size
                || Math.abs(colCount[col]) == size
                || row == size - col - 1 && Math.abs(revDiagCount) == size
                || row == col && Math.abs(diagCount) == size;
    }

//      not optimal
//    protected boolean isWinner(int row, int col, tictactoe.PlayingPiece playingPiece) {
//        boolean isRow = true, isCol = true, isDiagonal = true, isReverseDiagonal = true;
//        for (int i = 0; i < size; i++) {
//            if (board[row][i] != playingPiece) {
//                isRow = false;
//            }
//            if (board[i][col] != playingPiece) {
//                isCol = false;
//            }
//            if (row == col) {
//                if (board[i][i] != playingPiece) {
//                    isDiagonal = false;
//                }
//            }
//            if (row == size - col - 1) {
//                if (board[i][size - i - 1] != playingPiece) {
//                    isReverseDiagonal = false;
//                }
//            }
//        }
//        return isRow || isCol || isDiagonal || isReverseDiagonal;
//    }

    public List<Pair> getAvailableCells() {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == null) pairs.add(new Pair(i, j));
            }
        }
        return pairs;
    }

    public boolean isDraw() {
        return getAvailableCells().size() == 0;
    }

}
