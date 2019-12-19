package main;

import java.util.ArrayList;
import java.util.HashMap;

public class GameInput {
    private int xDim;
    private int yDim;
    private ArrayList<String> groundData;
    private int playerNumber;
    private ArrayList<Triplet<String, Integer, Integer>> playerData;
    private int roundNumber;
    private ArrayList<String> roundData;
    private HashMap<Integer, ArrayList<String>> angelsMap;

    public GameInput() {
        xDim = -1;
        yDim = -1;
        groundData = null;
        playerNumber = 0;
        playerData = null;
        roundNumber = 0;
        roundData = null;
    }

    GameInput(final int xDim, final int yDim, final ArrayList<String> groundData,
              final int playerNumber, final ArrayList<Triplet<String, Integer, Integer>> playerData,
              final int roundNumber, final ArrayList<String> roundData, final HashMap angelsMap) {
        this.xDim = xDim;
        this.yDim = yDim;
        this.groundData = groundData;
        this.playerNumber = playerNumber;
        this.playerData = playerData;
        this.roundNumber = roundNumber;
        this.roundData = roundData;
        this.angelsMap = angelsMap;
    }

    final int getXDim() {
        return xDim;
    }

    final int getYDim() {
        return yDim;
    }

    final ArrayList<String> getGroundData() {
        return groundData;
    }

    final int getPlayerNumber() {
        return playerNumber;
    }

    final ArrayList<Triplet<String, Integer, Integer>> getPlayerData() {
        return playerData;
    }

    final int getRoundNumber() {
        return roundNumber;
    }

    final ArrayList<String> getRoundData() {
        return roundData;
    }

    final HashMap getAngelsMap() {
        return angelsMap;
    }
}
