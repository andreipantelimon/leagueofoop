package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class LifeGiver extends Angel {
    public LifeGiver(final int id, final int xPos, final int yPos) {
        this.setId(id);
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setType("LifeGiver");
    }

    /**
     * Visit method for LifeGiver Angel.
     * @param player
     * @throws IOException
     */
    @Override
    public void visitPlayer(final Player player) throws IOException {
        String type = player.getType();
        int lifeToGive = Constants.getLifeGiverHp(type);
        if (player.getHp() + lifeToGive > player.getMaxHp()) {
            player.setHp(player.getMaxHp());
        } else {
            player.setHp(player.getHp() + lifeToGive);
        }
        this.notifyHelp(player);
    }

}
