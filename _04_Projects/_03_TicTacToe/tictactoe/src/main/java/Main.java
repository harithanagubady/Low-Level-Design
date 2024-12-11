import tictactoe.Player;
import tictactoe.PlayingPiece;
import tictactoe.TicTacToe;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("Haritha", PlayingPiece.X);
        Player player2 = new Player("Jacky", PlayingPiece.O);
        //Player player3 = new Player("Murphy", PlayingPiece.A);
        List<Player> players = List.of(player1, player2);
        TicTacToe ticTacToe = TicTacToe.getInstance(players, 3);
        ticTacToe.play();
    }
}
