package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class DamageAngel extends Angel {
    public DamageAngel(final int id, final int xPos, final int yPos) {
        this.setId(id);
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setType("DamageAngel");
    }

    /**
     * Visit method for Damage Angel.
     * @param player
     * @throws IOException
     */
    @Override
    public void visitPlayer(final Player player) throws IOException {
        String type = player.getType();
        switch (type) {
            case "K": player.addAngelPercent(Constants.DAMAGE_ANGEL_KNIGHT);
                break;
            case "W": player.addAngelPercent(Constants.DAMAGE_ANGEL_WIZARD);
                break;
            case "R": player.addAngelPercent(Constants.DAMAGE_ANGEL_ROGUE);
                break;
            case "P": player.addAngelPercent(Constants.DAMAGE_ANGEL_PYRO);
            default:
        }
        this.notifyHelp(player);
    }
}
