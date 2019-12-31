package angel;

import player.Player;

import java.io.IOException;

public interface AngelVisitor {
    void visitPlayer(Player player) throws IOException;
}
