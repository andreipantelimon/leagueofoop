package player;

import board.Ground;
import board.GroundType;

import static java.lang.Float.min;
import static java.lang.Integer.max;

public class Wizard extends Player {
    Wizard(int id, int xPos, int yPos) {
        super(id, xPos, yPos);
        this.hp = 400;
        this.maxHp = 400;
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
        int kill = 0;

        int groundModifier = 0;
        if (ground.getType().equals(GroundType.Desert)) {
            groundModifier = 10;
        }

        int drainPercent = 20 + 5 * this.level;
        float baseHp =min(0.3f * player.getMaxHp(), player.getHp());

        int drainModifier = 0;
        switch (player.getType()) {
            case "K": drainModifier = 20;
                break;
            case "P": drainModifier = -10;
                break;
            case "R": drainModifier = -20;
                break;
            case "W": drainModifier = 5;
        }
        drainPercent += Math.round(drainModifier * drainPercent / 100);
        drainPercent += Math.round(groundModifier * drainPercent / 100);
        int drainDmg = Math.round(drainPercent * baseHp / 100);

        player.setHp(player.getHp() - drainDmg);
        if (player.getHp() <= 0) {
            player.died();
            kill = 1;
        }

        if (!player.getType().equals("W")) {
            int deflectPercent = 35 + 2 * this.level;
            if (deflectPercent > 70) {
                deflectPercent = 70;
            }
            int deflectModifier = 0;
            switch (player.getType()) {
                case "K": deflectModifier = 40;
                    break;
                case "P": deflectModifier = 30;
                    break;
                case "R": deflectModifier = 20;
                    break;
            }
            int deflectDmg = Math.round(deflectPercent * player.getDamageToWizard() / 100);
            deflectDmg += Math.round(deflectModifier * deflectDmg / 100);
            deflectDmg += Math.round(groundModifier * deflectDmg / 100);

            player.setHp(player.getHp() - deflectDmg);
            if (player.getHp() <= 0) {
                player.died();
                kill = 1;
            }
        }

        player.resetDamageToWizard();
        if (kill == 1) {
            this.xp += max(0, 200 - (this.level - player.getLevel()) * 40);
            this.levelUp();
        }
    }

    void levelUp() {
        int oldLevel = this.level;
        super.levelUp();
        if (oldLevel > this.level) {
            this.hp = 400 + 30 * this.level;
        }
    }
}
