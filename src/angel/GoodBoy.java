package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class GoodBoy extends Angel {
    public GoodBoy(final int id, final int xPos, final int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = "GoodBoy";
    }

    @Override
    public void visitPlayer(Player player) throws IOException {
        String type = player.getType();
        System.out.println("Player " + type + " visited by good boy");

        switch (type) {
            case "K": player.addAngelPercent(Constants.GOOD_BOY_KNIGHT);
                break;
            case "W": player.addAngelPercent(Constants.GOOD_BOY_WIZARD);
                break;
            case "R": player.addAngelPercent(Constants.GOOD_BOY_ROGUE);
                break;
            case "P": player.addAngelPercent(Constants.GOOD_BOY_PYRO);
        }

        if (player.getHp() + Constants.getGoodBoyHp(type) > player.getMaxHp()) {
            player.setHp(player.getMaxHp());
        } else {
            player.setHp(player.getHp() + Constants.getGoodBoyHp(type));
        }
        this.notifyHelp(player);
    }

}
