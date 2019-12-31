package angel;

import main.GreatMagician;
import player.*;

import java.io.IOException;

public abstract class Angel implements AngelVisitor, AngelObservable {
    int id;
    int xPos;
    int yPos;
    String type;

    @Override
    public void visitPlayer(Player player) throws IOException {
        System.out.println("Player visited");
    }

    @Override
    public void notifyHelp(Player player) throws IOException {
        GreatMagician.getInstance().updateHelp(this, player);
    }

    @Override
    public void notifyKill(Player player) throws IOException {
        GreatMagician.getInstance().updateKill(this, player);
    }

    @Override
    public void notifySpawn() throws IOException {
        GreatMagician.getInstance().updateSpawn(this);
    }

    @Override
    public void notifyHit(Player player) throws IOException {
        GreatMagician.getInstance().updateHit(this, player);
    }

    @Override
    public void notifyRespawn(Player player) throws IOException {
        GreatMagician.getInstance().updateRespawn(player);
    }

    public String getType() {
        return type;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
}
