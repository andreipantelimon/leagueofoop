package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class DarkAngel extends Angel {
    public DarkAngel(final int id, final int xPos, final int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = "DarkAngel";
    }

    @Override
    public void visitPlayer(Player player) throws IOException {
        String type = player.getType();
        System.out.println("Player " + type + " visited by dark angel");

        int lifeToSteal = Constants.getDarkAngelHp(type);
        player.setHp(player.getHp() - lifeToSteal);
        this.notifyHit(player);
        if (player.getHp() <= 0) {
            player.died();
            this.notifyKill(player);
        }
    }
}
