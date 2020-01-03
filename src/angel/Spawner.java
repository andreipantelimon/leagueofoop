package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class Spawner extends Angel {
    public Spawner(final int id, final int xPos, final int yPos) {
        this.setId(id);
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setType("Spawner");
    }

    /**
     * Visit method for Spawner Angel.
     * @param player
     * @throws IOException
     */
    @Override
    public void visitPlayer(final Player player) throws IOException {
        String type = player.getType();
        if (player.isDead()) {
            player.alive();
            player.setHp(Constants.getSpawnerHp(type));
            this.notifyHelp(player);
            this.notifyRespawn(player);
        }
    }
}
