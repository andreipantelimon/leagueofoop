package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class Spawner extends Angel {
    public Spawner(final int id, final int xPos, final int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = "Spawner";
    }

    @Override
    public void visitPlayer(Player player) throws IOException {
        String type = player.getType();
        System.out.println("Player " + type + " visited by spawner");

        if (player.isDead()) {
            player.setDead(false);
            player.setHp(Constants.getSpawnerHp(type));
            this.notifyHelp(player);
            this.notifyRespawn(player);
        }
        //TODO: notify observer
    }
}
