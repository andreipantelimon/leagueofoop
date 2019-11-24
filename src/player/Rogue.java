package player;

import board.Ground;
import board.GroundType;

import static java.lang.Integer.max;

public class Rogue extends Player {
    int hitCounter = 0;
    Rogue(int id, int xPos, int yPos) {
        super(id, xPos, yPos);
        this.hp = 600;
        this.type = PlayerType.Rogue;
    }

    public String getType() {
        return "R";
    }

    public void accept(Player player, Ground ground) {
        player.fight(this, ground);
    }

    void fight(Player player, Ground ground) {
        System.out.println("Fight between " + this.getType() + " " + player.getType());

        int kill = 0;

        int backstabDmg = 200 + 20 * this.level;

        if (hitCounter == 2) {
            if (ground.getType().equals(GroundType.Woods)) {
                backstabDmg = Math.round(1.5f * backstabDmg);
                hitCounter = 0;
            } else {
                hitCounter = 0;
            }
        } else {
            hitCounter++;
        }

        int backstabModifier = 0;
        switch (player.getType()) {
            case "K": backstabModifier = -10;
                break;
            case "P":
            case "W":
                backstabModifier = 25;
                break;
            case "R": backstabModifier = 20;
                break;
        }

        backstabDmg += Math.round(backstabModifier * backstabDmg / 100);

        int groundModifier = 0;
        if (ground.getType().equals(GroundType.Woods)) {
            groundModifier = 15;
        }

        backstabDmg += Math.round(groundModifier * backstabDmg / 100);

        player.setHp(player.getHp() - backstabDmg);
        if (player.getHp() <= 0) {
            player.died();
            kill = 1;
        }

        int paralysisDmg = 40 + 10 * this.level;
        int paralysisModifier = 0;

        switch (player.getType()) {
            case "K": paralysisModifier = -20;
                break;
            case "P": paralysisModifier = 20;
                break;
            case "W":
                paralysisModifier = 25;
                break;
            case "R": paralysisModifier = -10;
                break;
        }

        paralysisDmg += Math.round(paralysisModifier * paralysisDmg / 100);
        paralysisDmg += Math.round(groundModifier * paralysisDmg / 100);

        //TODO: DoT + stun X rounds

        player.setHp(player.getHp() - paralysisDmg);

        if (player.getHp() <= 0) {
            player.died();
            kill = 1;
        }

        if (kill == 1) {
            this.xp += max(0, 200 - (this.level - player.getLevel()) * 40);
            //TODO: level up
        }
    }
}
