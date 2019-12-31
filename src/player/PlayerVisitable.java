package player;

import angel.AngelVisitor;

import java.io.IOException;

public interface PlayerVisitable {
    void acceptAngel(AngelVisitor angel) throws IOException;
}
