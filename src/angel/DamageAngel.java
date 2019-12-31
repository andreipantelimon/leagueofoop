package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class DamageAngel extends Angel {
    public DamageAngel(final int id, final int xPos, final int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = "DamageAngel";
    }

    @Override
    public void visitPlayer(Player player) throws IOException {
        String type = player.getType();
        System.out.println("Player " + type + " visited by damage Angel");

        switch (type) {
            case "K": player.addAngelPercent(Constants.DAMAGE_ANGEL_KNIGHT);
                break;
            case "W": player.addAngelPercent(Constants.DAMAGE_ANGEL_WIZARD);
                break;
            case "R": player.addAngelPercent(Constants.DAMAGE_ANGEL_ROGUE);
                break;
            case "P": player.addAngelPercent(Constants.DAMAGE_ANGEL_PYRO);
        }
        //TODO: notify observer
        this.notifyHelp(player);
    }
}
