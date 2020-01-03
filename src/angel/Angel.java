package angel;

import main.GreatMagician;
import player.Player;


import java.io.IOException;

public abstract class Angel implements AngelVisitor, AngelObservable {
    private int id;
    private int xPos;
    private int yPos;
    private String type;

    /**
     * Visit method. Every angel has its own.
     * @param player
     * @throws IOException
     */
    @Override
    public void visitPlayer(final Player player) throws IOException {
        System.out.println("Player visited");
    }

    //Different notify methods for notifying the Great Magician.
    @Override
    public final void notifyHelp(final Player player) throws IOException {
        GreatMagician.getInstance().updateHelp(this, player);
    }

    @Override
    public final void notifyKill(final Player player) throws IOException {
        GreatMagician.getInstance().updateKill(this, player);
    }

    @Override
    public final void notifySpawn() throws IOException {
        GreatMagician.getInstance().updateSpawn(this);
    }

    @Override
    public final void notifyHit(final Player player) throws IOException {
        GreatMagician.getInstance().updateHit(this, player);
    }

    @Override
    public final void notifyRespawn(final Player player) throws IOException {
        GreatMagician.getInstance().updateRespawn(player);
    }

    public final String getType() {
        return type;
    }

    public final int getxPos() {
        return xPos;
    }

    public final int getyPos() {
        return yPos;
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final void setxPos(final int xPos) {
        this.xPos = xPos;
    }

    public final void setyPos(final int yPos) {
        this.yPos = yPos;
    }

    public final void setType(final String type) {
        this.type = type;
    }
}
