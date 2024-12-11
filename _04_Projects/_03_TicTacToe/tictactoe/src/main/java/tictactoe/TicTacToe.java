package tictactoe;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {

    private static TicTacToe instance;

    private final Board board;

    private final Deque<Player> players;
    private boolean gameOver;

    private TicTacToe(List<Player> players, int boardSize) {
        this.board = BoardFactory.createBoard(boardSize, players);
        this.players = new ArrayDeque<>(players);
        this.gameOver = false;
    }

    public static TicTacToe getInstance(List<Player> players, int boardSize) {
        if (instance == null) {
            instance = new TicTacToe(players, boardSize);
        }
        return instance;
    }

    /*
    public void play() {
        Scanner scn = new Scanner(System.in);
        while (!gameOver) {
            tictactoe.Player currPlayer = players.removeFirst();
            int row, col;
            System.out.println(board.getAvailableCells());
            do {
                System.out.print("Enter row: ");
                row = scn.nextInt();
                System.out.print("Enter col: ");
                col = scn.nextInt();
            } while (!board.makeMove(row, col, currPlayer.getPlayingPiece()));

            if (board.isWinner(row, col, currPlayer.getPlayingPiece())) {
                board.printBoard();
                System.out.println(currPlayer.getPlayerName() + " wins!");
            } else if (board.isDraw()) {
                board.printBoard();
                System.out.println("It's a draw!");
            }
        }
    }
     */

    public void play() {
        Scanner scn = new Scanner(System.in);
        while (!gameOver) {
            Player currPlayer = players.removeFirst();
            int row, col;
            System.out.println(board.getAvailableCells());
            boolean isWinner = false;
            board.printBoard();
            try {
                System.out.print("Enter row: ");
                row = scn.nextInt();
                System.out.print("Enter col: ");
                col = scn.nextInt();
                isWinner = board.makeMove(row, col, currPlayer.getPlayingPiece());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                players.addFirst(currPlayer);
            }

            if (isWinner) {
                board.printBoard();
                System.out.println(currPlayer.getPlayerName() + " wins!");
                gameOver = true;
            } else if (board.isDraw()) {
                board.printBoard();
                System.out.println("It's a draw!");
                gameOver = true;
            }
            players.addLast(currPlayer);
        }
    }
}
