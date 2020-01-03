package angel;

import main.GameMaster;
import player.Player;

import java.io.IOException;

public class TheDoomer extends Angel {
    public TheDoomer(final int id, final int xPos, final int yPos) {
        this.setId(id);
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setType("TheDoomer");
    }

    /**
     * Visit method for TheDoomer Angel.
     * @param player
     * @throws IOException
     */
    @Override
    public void visitPlayer(final Player player) throws IOException {
        if (!player.isDead()) {
            player.died(GameMaster.getInstance().getBoard()[player.getxPos()][player.getyPos()]);
            this.notifyHit(player);
            this.notifyKill(player);
        }
    }
}
