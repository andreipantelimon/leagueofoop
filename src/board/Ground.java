package board;

import angel.Angel;
import player.Player;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Ground {
    // Class that keeps 2 players that will fight and ground type.
    private Player player1 = null;
    private Player player2 = null;
    private int numPlayers = 0;
    private GroundType type;
    private ArrayList<Angel> angelList = new ArrayList<>();
    private ArrayList<Player> deadPlayers = new ArrayList<>();
    private boolean fightCheck = false;

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

    public final ArrayList<Angel> getAngelList() {
        return angelList;
    }

    public final void addAngel(Angel angel) {
        this.angelList.add(angel);
    }

    public ArrayList<Player> getDeadPlayers() {
        return deadPlayers;
    }

    public void addDeadPlayers(Player dead) {
        this.deadPlayers.add(dead);
    }

    public final void removePlayer(Player player) {
        if (player1 != null) {
            if (player1.equals(player)) {
                player1 = null;
                numPlayers--;
            }
        }
        if (player2 != null){
            if (player2.equals(player)) {
                player2 = null;
                numPlayers--;
            }
        }
    }

    public boolean isFightCheck() {
        return fightCheck;
    }

    public void setFightCheck(boolean fightCheck) {
        this.fightCheck = fightCheck;
    }
}
