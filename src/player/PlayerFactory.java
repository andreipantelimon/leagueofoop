package player;

public class PlayerFactory {

    public static Player createPlayer(int id, char type, int xPos, int yPos) {
        switch (type) {
            case 'K': return new Knight(id, xPos, yPos);
            case 'P': return new Pyromancer(id, xPos, yPos);
            case 'R': return new Rogue(id, xPos, yPos);
            case 'W': return new Wizard(id, xPos, yPos);
        }
        throw new IllegalArgumentException("Type" + type + "not recognized.");
    }
}
