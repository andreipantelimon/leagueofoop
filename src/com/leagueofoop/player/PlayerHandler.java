package com.leagueofoop.player;

import java.util.ArrayList;

public class PlayerHandler {
    ArrayList<Player> playersList = new ArrayList<>();
    private static PlayerHandler instance = null;

    private PlayerHandler(){}

    public static PlayerHandler getInstance() {
        if (instance == null) {
            instance = new PlayerHandler();
        }
        return instance;
    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    public void addPlayer(Player player) {
        playersList.add(player);
    }
}
