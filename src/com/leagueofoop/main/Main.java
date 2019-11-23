package com.leagueofoop.main;

import com.leagueofoop.player.Player;
import com.leagueofoop.player.PlayerFactory;
import com.leagueofoop.player.PlayerHandler;

public final class Main {
    public static void main(String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();

        for (int i = 0; i < gameInput.getPlayerNumber(); i++) {
            char tempType = gameInput.getPlayerData().get(i).charAt(0);
            int tempXPos = Character.getNumericValue(gameInput.getPlayerData().get(i).charAt(1));
            int tempYPos = Character.getNumericValue(gameInput.getPlayerData().get(i).charAt(2));

            PlayerHandler.getInstance().addPlayer(PlayerFactory.createPlayer(i, tempType, tempXPos, tempYPos));
        }

        for (Player player : PlayerHandler.getInstance().getPlayersList()) {
            System.out.println(player);
        }
    }
}
