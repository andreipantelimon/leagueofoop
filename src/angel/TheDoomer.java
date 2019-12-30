package angel;

import player.Player;

public class TheDoomer extends Angel {
    public TheDoomer(final int id, final int xPos, final int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void visitPlayer(Player player) {
        System.out.println("Player visited by the doomer");
        player.setDead(true);
        //TODO: notify observer
    }
}
