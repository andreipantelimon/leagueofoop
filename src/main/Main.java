package main;

import player.*;

import java.io.IOException;

public final class Main {
    public static void main(String[] args) throws IOException {
        GameIOLoader gameIOLoader = new GameIOLoader(args[0], args[1]);
        GameInput gameInput = gameIOLoader.load();

        for (int i = 0; i < gameInput.getPlayerNumber(); i++) {
            char tempType = gameInput.getPlayerData().get(i).charAt(0);
            int tempXPos = Character.getNumericValue(gameInput.getPlayerData().get(i).charAt(1));
            int tempYPos = Character.getNumericValue(gameInput.getPlayerData().get(i).charAt(2));

            GameMaster.getInstance().addPlayer(PlayerFactory.createPlayer(i, tempType, tempXPos, tempYPos));
        }

        GameMaster.getInstance().playTheGame(gameInput);

        for (Player player : GameMaster.getInstance().getPlayersList()) {
            System.out.println(player);
            gameIOLoader.write(player.toString());
        }
    }
}
