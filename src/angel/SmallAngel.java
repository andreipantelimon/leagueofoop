package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class SmallAngel extends Angel {
    public SmallAngel(final int id, final int xPos, final int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = "SmallAngel";
    }

    @Override
    public void visitPlayer(Player player) throws IOException {
        String type = player.getType();
        System.out.println("Player " + type + " visited by small angel");

        player.setHp(player.getHp() + Constants.getSmallAngelHp(type));

        switch (type) {
            case "K": player.addAngelPercent(Constants.SMALL_ANGEL_KNIGHT);
                break;
            case "W": player.addAngelPercent(Constants.SMALL_ANGEL_WIZARD);
                break;
            case "R": player.addAngelPercent(Constants.SMALL_ANGEL_ROGUE);
                break;
            case "P": player.addAngelPercent(Constants.SMALL_ANGEL_PYRO);
        }
        //TODO: notify observer
        this.notifyHelp(player);
    }
}
