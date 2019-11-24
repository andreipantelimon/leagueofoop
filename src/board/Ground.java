package board;

import player.Player;

public abstract class Ground {
    Player player1 = null;
    Player player2 = null;
    int numPlayers = 0;
    GroundType type;

    public String toString() {
        return "Type: " + this.type + " Player1: " + this.player1 + " Player2: " + this.player2;
    }


    public void setPlayer1(Player player) {
        this.player1 = player;
        numPlayers++;
    }

    public void setPlayer2(Player player) {
        this.player2 = player;
        numPlayers++;
    }


    public GroundType getType() {
        return this.type;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
