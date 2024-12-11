package tictactoe;

import lombok.Getter;

public enum PlayingPiece {
    //X(-1), O(1);

    //Uncomment this for multiplayer support
    X(1), O(2), A(3), H(4);


    @Getter private final int value;

    PlayingPiece (int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name();
    }
}
