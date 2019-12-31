package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class LifeGiver extends Angel {
    public LifeGiver(final int id, final int xPos, final int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = "LifeGiver";
    }

    @Override
    public void visitPlayer(Player player) throws IOException {
        String type = player.getType();
        System.out.println("Player " + type + " visited by lifegiver");

        int lifeToGive = Constants.getLifeGiverHp(type);
        System.out.println(player.getMaxHp());
        if (player.getHp() + lifeToGive > player.getMaxHp()) {
            player.setHp(player.getMaxHp());
        } else {
            player.setHp(player.getHp() + lifeToGive);
        }
        //TODO: notify observer
        this.notifyHelp(player);
    }

}
