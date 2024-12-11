package tictactoe;

import lombok.Getter;

import java.util.List;

public abstract class Board {

    @Getter
    public final PlayingPiece[][] board;
    @Getter
    public final int size;
    private static final int MAX_CAPACITY = 5;
    private static final int DEFAULT = 3;

    public Board(int size) {
        this.size = (size > MAX_CAPACITY || size < 3) ? DEFAULT : size;
        this.board = new PlayingPiece[size][size];
    }

    public abstract boolean makeMove(int row, int col, PlayingPiece playingPiece);

    public abstract List<Pair> getAvailableCells();


    static class Pair {
        int row, col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "( " + row + ", " + col + " )";
        }
    }

    public void printBoard() {

        for (int i = 0; i < size; i++) {
            System.out.print(" | ");
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].name() + "   ");
                } else {
                    System.out.print("    ");
                }
                System.out.print(" | ");
            }
            System.out.println();

        }
    }


    public boolean isDraw() {
        return getAvailableCells().size() == 0;
    }
}
