package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class GoodBoy extends Angel {
    public GoodBoy(final int id, final int xPos, final int yPos) {
        this.setId(id);
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setType("GoodBoy");
    }

    /**
     * Visit method for GoodBoy Angel.
     * @param player
     * @throws IOException
     */
    @Override
    public void visitPlayer(final Player player) throws IOException {
        String type = player.getType();
        switch (type) {
            case "K": player.addAngelPercent(Constants.GOOD_BOY_KNIGHT);
                break;
            case "W": player.addAngelPercent(Constants.GOOD_BOY_WIZARD);
                break;
            case "R": player.addAngelPercent(Constants.GOOD_BOY_ROGUE);
                break;
            case "P": player.addAngelPercent(Constants.GOOD_BOY_PYRO);
            default:
        }

        if (player.getHp() + Constants.getGoodBoyHp(type) > player.getMaxHp()) {
            player.setHp(player.getMaxHp());
        } else {
            player.setHp(player.getHp() + Constants.getGoodBoyHp(type));
        }
        this.notifyHelp(player);
    }

}
