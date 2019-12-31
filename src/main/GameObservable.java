package main;

import player.Player;

import java.io.IOException;

public interface GameObservable {
    void notifyDead(Player p1, Player p2) throws IOException;
}
