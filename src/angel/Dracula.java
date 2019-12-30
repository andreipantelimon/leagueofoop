package angel;

import main.Constants;
import player.Player;

public class Dracula extends Angel {
    public Dracula(final int id, final int xPos, final int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void visitPlayer(Player player) {
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
    }
}
