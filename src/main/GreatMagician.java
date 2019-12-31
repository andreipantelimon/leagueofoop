package main;

import angel.Angel;
import player.Player;

import java.io.IOException;

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
    public void updateHelp(Angel angel, Player player) throws IOException {
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
    public void updateHit(Angel angel, Player player) throws IOException {
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
    public void updateKill(Angel angel, Player player) throws IOException {
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
    public void updateSpawn(Angel angel) throws IOException {
        GameIOLoader.write("Angel " + angel.getType() + " was spawned at " + angel.getxPos() + " " + angel.getyPos());
    }

    @Override
    public void updateDead(Player p1, Player p2) throws IOException {
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
        GameIOLoader.write("Player " + type2 + " " + p2.getId() + " was killed by " + type1 + " " + p1.getId());
    }

    @Override
    public void updateLevelUp(Player player) throws IOException {
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
    public void updateRespawn(Player player) throws IOException {
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
        GameIOLoader.write("Player " + type + " " + player.getId() + " was brought to life by an angel");
    }
}
