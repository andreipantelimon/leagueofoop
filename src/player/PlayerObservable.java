package player;

import java.io.IOException;

public interface PlayerObservable {
    void notifyLevelUp() throws IOException;
}
