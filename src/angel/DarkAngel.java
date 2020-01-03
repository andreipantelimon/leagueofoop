package angel;

import main.Constants;
import main.GameMaster;
import player.Player;

import java.io.IOException;

public class DarkAngel extends Angel {
    public DarkAngel(final int id, final int xPos, final int yPos) {
        this.setId(id);
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setType("DarkAngel");
    }

    /**
     * Visit method for Dark Angel.
     * @param player
     * @throws IOException
     */
    @Override
    public void visitPlayer(final Player player) throws IOException {
        String type = player.getType();
        int lifeToSteal = Constants.getDarkAngelHp(type);
        player.setHp(player.getHp() - lifeToSteal);
        this.notifyHit(player);
        if (player.getHp() <= 0) {
            player.died(GameMaster.getInstance().getBoard()[player.getxPos()][player.getyPos()]);
            this.notifyKill(player);
        }
    }
}
