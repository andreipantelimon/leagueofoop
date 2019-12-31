package angel;

import player.Player;

import java.io.IOException;

public interface AngelObservable {
    void notifyHelp(Player player) throws IOException;
    void notifyKill(Player player) throws IOException;
    void notifySpawn() throws IOException;
    void notifyHit(Player player) throws IOException;
    void notifyRespawn(Player player) throws IOException;
}
