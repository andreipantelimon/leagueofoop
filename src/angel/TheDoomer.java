package angel;

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
        System.out.println("Player visited by the doomer");
        player.setDead(true);
        //TODO: notify observer
        this.notifyHit(player);
        this.notifyKill(player);
    }
}
