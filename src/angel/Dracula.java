package angel;

import main.Constants;
import main.GameMaster;
import player.Player;

import java.io.IOException;

public class Dracula extends Angel {
    public Dracula(final int id, final int xPos, final int yPos) {
        this.setId(id);
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setType("Dracula");
    }

    /**
     * Visit function for Dracula type Angel.
     * @param player
     * @throws IOException
     */
    @Override
    public void visitPlayer(final Player player) throws IOException {
        String type = player.getType();
        player.setHp(player.getHp() - Constants.getDraculaHp(type));

        switch (type) {
            case "K": player.addAngelPercent(Constants.DRACULA_KNIGHT);
                break;
            case "W": player.addAngelPercent(Constants.DRACULA_WIZARD);
                break;
            case "R": player.addAngelPercent(Constants.DRACULA_ROGUE);
                break;
            case "P": player.addAngelPercent(Constants.DRACULA_PYRO);
            default:
        }
        this.notifyHit(player);
        if (player.getHp() <= 0) {
            player.died(GameMaster.getInstance().getBoard()[player.getxPos()][player.getyPos()]);
            this.notifyKill(player);
        }
    }
}
