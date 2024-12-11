package tictactoe;

import java.util.List;

public class BoardFactory {

    public static Board createBoard (int size, List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("tictactoe.Player list cannot be null or empty");
        }

        if (players.size() < 2) {
            throw new IllegalArgumentException("Atleast two players expected");
        }

        if (players.size() == 2) {
            return new TwoPlayerBoard(size);
        } else {
            return new MultiplayerBoard(size);
        }
    }
}
