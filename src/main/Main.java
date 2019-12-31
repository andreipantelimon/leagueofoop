package main;

import player.Player;
import player.PlayerFactory;

import java.io.IOException;

public final class Main {
    private Main() { }
    public static void main(final String[] args) throws IOException {
        //Game input is read.
        GameIOLoader gameIOLoader = new GameIOLoader(args[0], args[1]);
        GameInput gameInput = gameIOLoader.load();

        for (int i = 0; i < gameInput.getPlayerNumber(); i++) {
            char tempType = gameInput.getPlayerData().get(i).getFirst().charAt(0);
            int tempXPos = gameInput.getPlayerData().get(i).getSecond();
            int tempYPos = gameInput.getPlayerData().get(i).getThird();

            // The factory creates the players.
            GameMaster.getInstance().addPlayer(
                    PlayerFactory.createPlayer(i, tempType, tempXPos, tempYPos));
        }

        //Game is played.
        GameMaster.getInstance().playTheGame(gameInput, gameIOLoader);

        //Output is written to the file.
        GameIOLoader.write("~~ Results ~~");
        for (Player player : GameMaster.getInstance().getPlayersList()) {
            GameIOLoader.write(player.toString());
        }
    }
}
