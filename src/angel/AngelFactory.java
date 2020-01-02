package angel;

import main.GreatMagician;

public class AngelFactory {
    public AngelFactory(){ }

    public Angel createAngel(final int id, final String type, final int xPos, final int yPos) {
        switch (type) {
            case "DamageAngel": return new DamageAngel(id, xPos, yPos);
            case "DarkAngel": return new DarkAngel(id, xPos, yPos);
            case "Dracula": return new Dracula(id, xPos, yPos);
            case "GoodBoy": return new GoodBoy(id, xPos, yPos);
            case "LevelUpAngel": return new LevelUpAngel(id, xPos, yPos);
            case "LifeGiver": return new LifeGiver(id, xPos, yPos);
            case "SmallAngel": return new SmallAngel(id, xPos, yPos);
            case "Spawner": return new Spawner(id, xPos, yPos);
            case "TheDoomer": return new TheDoomer(id, xPos, yPos);
            case "XPAngel": return new XPAngel(id, xPos, yPos);
            default:
        }
        throw new IllegalArgumentException("Type" + type + "not recognized.");
    }
}
