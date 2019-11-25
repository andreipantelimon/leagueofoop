package player;

import board.Ground;
import board.GroundType;

import static java.lang.Integer.max;

public class Pyromancer extends Player {
    Pyromancer(int id, int xPos, int yPos) {
        super(id, xPos, yPos);
        this.hp = 500;
        this.maxHp = 500;
        this.type = PlayerType.Pyromancer;
    }

    public String getType() {
        return "P";
    }

    public void accept(Player player, Ground ground) {
        player.fight(this, ground);
    }

    void fight(Player player, Ground ground) {
        System.out.println("Fight between " + this.getType() + " " + player.getType());

        int kill = 0;

        int fireblastDmg = 350 + 50 * this.level;

        int fireblastModifier = 0;
        switch (player.getType()) {
            case "K": fireblastModifier = 20;
                break;
            case "P": fireblastModifier = -10;
                break;
            case "R": fireblastModifier = -20;
                break;
            case "W": fireblastModifier = 5;
        }

        fireblastDmg += Math.round(fireblastModifier * fireblastDmg / 100);

        int groundModifier = 0;
        if (ground.getType().equals(GroundType.Volcanic)) {
            groundModifier = 25;
        }
        fireblastDmg += Math.round(groundModifier * fireblastDmg / 100);

        player.setHp(player.getHp() - fireblastDmg);

        if (player.getHp() <= 0) {
            player.died();
            kill = 1;
        }

        int igniteDmg = 150 + 20 * this.level;
        int ignitePerRound = 50 + 30 * this.level;

        int igniteModifier = 0;
        switch (player.getType()) {
            case "K": igniteModifier = 20;
                break;
            case "P": igniteModifier = -10;
                break;
            case "R": igniteModifier = -20;
                break;
            case "W": igniteModifier = 5;
        }

        igniteDmg += Math.round(igniteModifier * igniteDmg / 100);
        igniteDmg += Math.round(groundModifier * igniteDmg / 100);
        ignitePerRound += Math.round(igniteModifier * ignitePerRound / 100);
        ignitePerRound += Math.round(groundModifier * ignitePerRound / 100);

        player.setHp(player.getHp() - igniteDmg);

        //TODO: ignite DoT

        if (player.getHp() <= 0) {
            player.died();
            kill = 1;
        }

        if (kill == 1) {
            this.xp += max(0, 200 - (this.level - player.getLevel()) * 40);
            this.levelUp();
        }
    }

    void levelUp() {
        int oldLevel = this.level;
        super.levelUp();
        if (oldLevel > this.level) {
            this.hp = 500 + 50 * this.level;
        }
    }
}
