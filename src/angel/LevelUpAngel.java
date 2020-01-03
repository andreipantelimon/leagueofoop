package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class LevelUpAngel extends Angel {
    public LevelUpAngel(final int id, final int xPos, final int yPos) {
        this.setId(id);
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setType("LevelUpAngel");
    }

    /**
     * Visit method for LevelUp Angel.
     * @param player
     * @throws IOException
     */
    @Override
    public void visitPlayer(final Player player) throws IOException {
        String type = player.getType();
        int xpNeeded = (Constants.LEVELUP_BASE_XP
                + player.getLevel() * Constants.LEVELUP_LEVEL_XP) - player.getXp();
        player.addXp(xpNeeded);

        switch (type) {
            case "K": player.addAngelPercent(Constants.LEVELUP_ANGEL_KNIGHT);
                break;
            case "W": player.addAngelPercent(Constants.LEVELUP_ANGEL_WIZARD);
                break;
            case "R": player.addAngelPercent(Constants.LEVELUP_ANGEL_ROGUE);
                break;
            case "P": player.addAngelPercent(Constants.LEVELUP_ANGEL_PYRO);
            default:
        }
        this.notifyHelp(player);
        player.levelUp();
    }
}
