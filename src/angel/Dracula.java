package angel;

import main.Constants;
import player.Player;

import java.io.IOException;

public class Dracula extends Angel {
    public Dracula(final int id, final int xPos, final int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = "Dracula";
    }

    @Override
    public void visitPlayer(Player player) throws IOException {
        String type = player.getType();
        System.out.println("Player " + type + " visited by dracula");

        player.setHp(player.getHp() - Constants.getDraculaHp(type));

        switch (type) {
            case "K": player.addAngelPercent(Constants.DRACULA_KNIGHT);
                break;
            case "W": player.addAngelPercent(Constants.DRACULA_WIZARD);
                break;
            case "R": player.addAngelPercent(Constants.DRACULA_ROGUE);
                break;
            case "P": player.addAngelPercent(Constants.DRACULA_PYRO);
        }
        //TODO: notify observer
        this.notifyHit(player);
        if (player.getHp() <= 0) {
            player.died();
            this.notifyKill(player);
        }
    }
}
