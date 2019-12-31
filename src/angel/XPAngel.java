package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class XPAngel extends Angel {
    public XPAngel(final int id, final int xPos, final int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = "XPAngel";
    }

    @Override
    public void visitPlayer(Player player) throws IOException {
        String type = player.getType();
        System.out.println("Player " + type + " visited by xp angel");

        int xpToGive = Constants.getXpAngelXp(type);
        player.addXp(xpToGive);
        this.notifyHelp(player);
        player.levelUp();
    }
}
