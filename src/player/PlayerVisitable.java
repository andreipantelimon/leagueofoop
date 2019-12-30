package player;

import angel.AngelVisitor;

public interface PlayerVisitable {
    void acceptAngel(AngelVisitor angel);
}
