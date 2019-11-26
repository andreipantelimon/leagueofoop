package player;

public final class PlayerFactory {

    //Factory pattern.
    private PlayerFactory() { }

    public static Player createPlayer(final int id, final char type,
                                      final int xPos, final int yPos) {
        switch (type) {
            case 'K': return new Knight(id, xPos, yPos);
            case 'P': return new Pyromancer(id, xPos, yPos);
            case 'R': return new Rogue(id, xPos, yPos);
            case 'W': return new Wizard(id, xPos, yPos);
            default:
        }
        throw new IllegalArgumentException("Type" + type + "not recognized.");
    }
}
