package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class SmallAngel extends Angel {
    public SmallAngel(final int id, final int xPos, final int yPos) {
        this.setId(id);
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setType("SmallAngel");
    }

    /**
     * Visit method for Small Angel.
     * @param player
     * @throws IOException
     */
    @Override
    public void visitPlayer(final Player player) throws IOException {
        String type = player.getType();
        player.setHp(player.getHp() + Constants.getSmallAngelHp(type));
        switch (type) {
            case "K": player.addAngelPercent(Constants.SMALL_ANGEL_KNIGHT);
                break;
            case "W": player.addAngelPercent(Constants.SMALL_ANGEL_WIZARD);
                break;
            case "R": player.addAngelPercent(Constants.SMALL_ANGEL_ROGUE);
                break;
            case "P": player.addAngelPercent(Constants.SMALL_ANGEL_PYRO);
            default:
        }
        this.notifyHelp(player);
    }
}
