package tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiplayerBoard extends Board {

    Map<Integer, Map<PlayingPiece, Integer>> rowCount;//(rowNo, (piece, count))
    Map<Integer, Map<PlayingPiece, Integer>> colCount;
    Map<PlayingPiece, Integer> diagCount;
    Map<PlayingPiece, Integer> revDiagCount;

    public MultiplayerBoard(int size) {
        super(size);
        System.out.println("Welcome to Multiplayer tic tac toe... ");
        rowCount = new HashMap<>();
        colCount = new HashMap<>();
        diagCount = new HashMap<>();
        revDiagCount = new HashMap<>();
    }

    public boolean makeMove(int row, int col, PlayingPiece playingPiece) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IllegalArgumentException("Out of Bounds");
        }
        if (board[row][col] != null) {
            throw new IllegalArgumentException("Incorrect placement");
        }
        board[row][col] = playingPiece;
        rowCount.putIfAbsent(row, new HashMap<>());
        colCount.putIfAbsent(col, new HashMap<>());

        rowCount.get(row).put(playingPiece, rowCount.get(row).getOrDefault(playingPiece, 0) + 1);
        colCount.get(col).put(playingPiece, colCount.get(col).getOrDefault(playingPiece, 0) + 1);
        if (row == col) {
            diagCount.put(playingPiece, diagCount.getOrDefault(playingPiece, 0) + 1);
        }
        if (row == size - col - 1) {
            revDiagCount.put(playingPiece, revDiagCount.getOrDefault(playingPiece, 0) + 1);
        }

        return isWinner(row, col, playingPiece);
    }

    protected boolean isWinner(int row, int col, PlayingPiece playingPiece) {
        return rowCount.get(row).get(playingPiece) == size
                || colCount.get(col).get(playingPiece) == size
                || (row == col && diagCount.get(playingPiece) == size)
                || (row == size - col - 1 && revDiagCount.get(playingPiece) == size);
    }

    public List<Pair> getAvailableCells() {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == null) pairs.add(new Pair(i, j));
            }
        }
        return pairs;
    }


}
