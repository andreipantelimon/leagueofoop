package board;

import player.Player;

public abstract class Ground {
    // Class that keeps 2 players that will fight and ground type.
    private Player player1 = null;
    private Player player2 = null;
    private int numPlayers = 0;
    private GroundType type;

    public final void setPlayer1(final Player player) {
        this.player1 = player;
        numPlayers++;
    }

    public final void setPlayer2(final Player player) {
        this.player2 = player;
        numPlayers++;
    }

    public final void setNumPlayers(final int num) {
        this.numPlayers = num;
    }

    public final GroundType getType() {
        return this.type;
    }

    public final int getNumPlayers() {
        return this.numPlayers;
    }

    public final Player getPlayer1() {
        return player1;
    }

    public final Player getPlayer2() {
        return player2;
    }

    public final void setType(final GroundType type) {
        this.type = type;
    }
}
