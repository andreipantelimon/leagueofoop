package angel;

import player.*;

public abstract class Angel implements AngelVisitor {
    int id;
    int xPos;
    int yPos;

    @Override
    public void visitPlayer(Player player) {
        System.out.println("Player visited");
    }
}
