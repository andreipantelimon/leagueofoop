package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class LevelUpAngel extends Angel {
    public LevelUpAngel(final int id, final int xPos, final int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = "LevelUpAngel";
    }

    @Override
    public void visitPlayer(Player player) throws IOException {
        String type = player.getType();
        System.out.println("Player " + type + " visited by levelup Angel");
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
        }
        this.notifyHelp(player);
        player.levelUp();
    }
}
