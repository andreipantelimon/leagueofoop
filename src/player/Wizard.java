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
        int kill = 0;
        boolean playerDied = false;

        float groundModifier = 0;
        if (ground.getType().equals(GroundType.Desert)) {
            groundModifier = 0.1f;
        }

        float drainPercent = 0.2f + 0.05f * this.level;
        float baseHp = min(0.3f * player.getMaxHp(), player.getHp());

        float drainPercentAfterGround = drainPercent + (groundModifier * drainPercent);

        float drainModifier = 0;
        switch (player.getType()) {
            case "K": drainModifier = 0.2f;
                break;
            case "P": drainModifier = -0.1f;
                break;
            case "R": drainModifier = -0.2f;
                break;
            case "W": drainModifier = 0.05f;
        }
        float drainPercentAfterRace = drainPercentAfterGround + (drainModifier * drainPercentAfterGround);

        int drainDmg = Math.round(drainPercentAfterRace * baseHp);

        player.setHp(player.getHp() - drainDmg);
        if (player.getHp() <= 0) {
            player.died();
            kill = 1;
            playerDied = true;
        }

        if (!player.getType().equals("W")) {
            float deflectPercent = 0.35f + 0.2f * this.level;
            deflectPercent = Math.min(deflectPercent, 0.7f);

            float deflectPercentAfterGround = deflectPercent + (groundModifier * deflectPercent);

            float deflectModifier = 0;
            switch (player.getType()) {
                case "K": deflectModifier = 0.4f;
                    break;
                case "P": deflectModifier = 0.3f;
                    break;
                case "R": deflectModifier = 0.2f;
                    break;
            }
            float deflectPercentAfterRace = deflectPercentAfterGround + (deflectModifier * deflectPercentAfterGround);
            int deflectDmg = Math.round(deflectPercentAfterRace * player.getDamageToWizard());

            player.setHp(player.getHp() - deflectDmg);
            if (player.getHp() <= 0 && !playerDied) {
                player.died();
                kill = 1;
            }
        }

        player.resetDamageToWizard();
        if (kill == 1) {
            this.xp += max(0, 200 - (this.level - player.getLevel()) * 40);
        }
    }

    public void levelUp() {
        int oldLevel = this.level;
        super.levelUp();
        if (this.level > oldLevel) {
            this.hp = 400 + 30 * this.level;
        }
    }
}
