package main;

import angel.Angel;
import player.Player;

import java.io.IOException;

public interface GreatMagicianObserver {
    void updateHelp(Angel angel, Player player) throws IOException;
    void updateHit(Angel angel, Player player) throws IOException;
    void updateKill(Angel angel, Player player) throws IOException;
    void updateSpawn(Angel angel) throws IOException;
    void updateDead(Player p1, Player p2) throws IOException;
    void updateLevelUp(Player player) throws IOException;
    void updateRespawn(Player player) throws IOException;
}
