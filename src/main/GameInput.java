package main;

import java.util.ArrayList;

public class GameInput {
    private int xDim;
    private int yDim;
    private ArrayList<String> groundData;
    private int playerNumber;
    private ArrayList<String> playerData;
    private int roundNumber;
    private ArrayList<String> roundData;

    public GameInput() {
        xDim = -1;
        yDim = -1;
        groundData = null;
        playerNumber = 0;
        playerData = null;
        roundNumber = 0;
        roundData = null;
    }

    GameInput(final int xDim, final int yDim, final ArrayList<String> groundData, final int playerNumber,
              final ArrayList<String> playerData, final int roundNumber, final ArrayList<String> roundData) {
        this.xDim = xDim;
        this.yDim = yDim;
        this.groundData = groundData;
        this.playerNumber = playerNumber;
        this.playerData = playerData;
        this.roundNumber = roundNumber;
        this.roundData = roundData;
    }

    public int getXDim() {
        return xDim;
    }

    public int getYDim() {
        return yDim;
    }

    public ArrayList<String> getGroundData() {
        return groundData;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public ArrayList<String> getPlayerData() {
        return playerData;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public ArrayList<String> getRoundData() {
        return roundData;
    }
}
