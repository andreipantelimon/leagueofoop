package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class XPAngel extends Angel {
    public XPAngel(final int id, final int xPos, final int yPos) {
        this.setId(id);
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setType("XPAngel");
    }

    /**
     * Visit method for XPAngel.
     * @param player
     * @throws IOException
     */
    @Override
    public void visitPlayer(final Player player) throws IOException {
        String type = player.getType();
        int xpToGive = Constants.getXpAngelXp(type);
        player.addXp(xpToGive);
        this.notifyHelp(player);
        player.levelUp();
    }
}
