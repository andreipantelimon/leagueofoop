package angel;

import main.GameMaster;
import player.Player;

import java.io.IOException;

public class TheDoomer extends Angel {
    public TheDoomer(final int id, final int xPos, final int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = "TheDoomer";
    }

    @Override
    public void visitPlayer(Player player) throws IOException {
        if (!player.isDead()) {
            System.out.println("Player visited by the doomer");
            player.died(GameMaster.getInstance().getBoard()[player.getxPos()][player.getyPos()]);
            this.notifyHit(player);
            this.notifyKill(player);
        }
    }
}
