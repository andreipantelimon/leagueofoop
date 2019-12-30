package angel;

import player.Player;

public class LifeGiver extends Angel {
    public LifeGiver(final int id, final int xPos, final int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void visitPlayer(Player player) {
        String type = player.getType();
        System.out.println("Player " + type + " visited by lifegiver");
        switch (type) {
            case "K": player.setHp(player.getHp() + 100);
                break;
            case "W": player.setHp(player.getHp() + 120);
                break;
            case "R": player.setHp(player.getHp() + 90);
                break;
            case "P": player.setHp(player.getHp() + 80);
        }
        //TODO: notify observer
    }
}
