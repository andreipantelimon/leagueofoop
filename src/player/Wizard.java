package player;

import board.Ground;

public class Wizard extends Player {
    Wizard(int id, int xPos, int yPos) {
        super(id, xPos, yPos);
        this.hp = 400;
        this.type = PlayerType.Wizard;
    }

    public String getType() {
        return "W";
    }

    public void accept(Player player, Ground ground) {
        player.fight(this, ground);
    }

    void fight(Player player, Ground ground) {
        System.out.println("Fight between " + this.getType() + " " + player.getType());
    }
}
