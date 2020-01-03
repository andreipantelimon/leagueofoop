package main;

import angel.Angel;
import player.Player;

import java.io.IOException;

//Observer pattern for Great Magician that writes in output.

public class GreatMagician implements GreatMagicianObserver {
    private static GreatMagician instance = null;

    public GreatMagician() {
    }

    public static GreatMagician getInstance() {
        if (instance == null) {
            instance = new GreatMagician();
        }
        return instance;
    }

    @Override
    public final void updateHelp(final Angel angel, final Player player) throws IOException {
        String type;
        switch (player.getType()) {
            case "K": type = "Knight";
                break;
            case "W": type = "Wizard";
                break;
            case "R": type = "Rogue";
                break;
            case "P": type = "Pyromancer";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + player.getType());
        }
        GameIOLoader.write(angel.getType() + " helped " + type + " " + player.getId());
    }

    @Override
    public final void updateHit(final Angel angel, final Player player) throws IOException {
        String type;
        switch (player.getType()) {
            case "K": type = "Knight";
                break;
            case "W": type = "Wizard";
                break;
            case "R": type = "Rogue";
                break;
            case "P": type = "Pyromancer";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + player.getType());
        }
        GameIOLoader.write(angel.getType() + " hit " + type + " " + player.getId());
    }

    @Override
    public final void updateKill(final Angel angel, final Player player) throws IOException {
        String type;
        switch (player.getType()) {
            case "K": type = "Knight";
                break;
            case "W": type = "Wizard";
                break;
            case "R": type = "Rogue";
                break;
            case "P": type = "Pyromancer";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + player.getType());
        }
        GameIOLoader.write("Player " + type + " " + player.getId() + " was killed by an angel");
    }

    @Override
    public final void updateSpawn(final Angel angel) throws IOException {
        GameIOLoader.write("Angel " + angel.getType() + " was spawned at "
                + angel.getxPos() + " " + angel.getyPos());
    }

    @Override
    public final void updateDead(final Player p1, final Player p2) throws IOException {
        String type1, type2;
        switch (p1.getType()) {
            case "K": type1 = "Knight";
                break;
            case "W": type1 = "Wizard";
                break;
            case "R": type1 = "Rogue";
                break;
            case "P": type1 = "Pyromancer";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + p1.getType());
        }

        switch (p2.getType()) {
            case "K": type2 = "Knight";
                break;
            case "W": type2 = "Wizard";
                break;
            case "R": type2 = "Rogue";
                break;
            case "P": type2 = "Pyromancer";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + p2.getType());
        }
        GameIOLoader.write("Player " + type2 + " " + p2.getId()
                + " was killed by " + type1 + " " + p1.getId());
    }

    @Override
    public final void updateLevelUp(final Player player) throws IOException {
        String type;
        switch (player.getType()) {
            case "K": type = "Knight";
                break;
            case "W": type = "Wizard";
                break;
            case "R": type = "Rogue";
                break;
            case "P": type = "Pyromancer";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + player.getType());
        }
        GameIOLoader.write(type + " " + player.getId() + " reached level " + player.getLevel());
    }

    @Override
    public final void updateRespawn(final Player player) throws IOException {
        String type;
        switch (player.getType()) {
            case "K": type = "Knight";
                break;
            case "W": type = "Wizard";
                break;
            case "R": type = "Rogue";
                break;
            case "P": type = "Pyromancer";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + player.getType());
        }
        GameIOLoader.write("Player " + type + " " + player.getId()
                + " was brought to life by an angel");
    }
}
