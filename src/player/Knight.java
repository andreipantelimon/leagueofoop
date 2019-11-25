package player;

import board.Ground;
import board.GroundType;

import static java.lang.Integer.max;

public class Knight extends Player {
    Knight(int id, int xPos, int yPos) {
        super(id, xPos, yPos);
        this.hp = 900;
        this.maxHp = 900;
        this.type = PlayerType.Knight;
    }

    public String getType() {
        return "K";
    }

    public void accept(Player player, Ground ground) {
        player.fight(this, ground);
    }

    void fight(Player player, Ground ground) {
        int kill = 0;
        boolean playerDied = false;
        float executeDmg = 200 + 30 * this.level;
        int executeHpLimit = Math.round((20 + this.level) * player.getHp() / 100);
        if (((20 + this.level) * player.getHp()) > 40) {
            executeHpLimit = Math.round(40 * player.getHp() / 100);
        }

        float groundModifier = 0;
        if (ground.getType().equals(GroundType.Land)) {
            groundModifier = 0.15f;
        }

        float executeModifier = 0;
        switch (player.getType()) {
            case "K": executeModifier = 0;
            break;
            case "P": executeModifier = 0.1f;
            break;
            case "R": executeModifier = 0.15f;
            break;
            case "W": executeModifier = -0.2f;
        }
        float executeDmgAfterGround = executeDmg + (groundModifier * executeDmg);

        this.damageToWizard += Math.round(executeDmgAfterGround);

        int executeDmgAfterRace = Math.round(executeDmgAfterGround + executeModifier * executeDmgAfterGround);

        if (player.getHp() <= executeHpLimit) {
            int executeDmgAll = player.getHp();
            player.setHp(player.getHp() - executeDmgAll);
            player.died();
            kill = 1;
            playerDied = true;
        } else {
            player.setHp(player.getHp() - executeDmgAfterRace);
        }

        float slamDmg = 100 + 40 * this.level;
        float slamModifier = 0;

        float slamDmgAfterGround = slamDmg + (groundModifier * slamDmg);

        this.damageToWizard += Math.round(slamDmgAfterGround);

        switch (player.getType()) {
            case "K": slamModifier = 0.2f;
                break;
            case "P": slamModifier = -0.1f;
                break;
            case "R": slamModifier = -0.2f;
                break;
            case "W": slamModifier = 0.05f;
        }
        int slamDmgAfterRace = Math.round(slamDmgAfterGround + slamModifier * slamDmgAfterGround);

        player.setHp(player.getHp() - slamDmgAfterRace);
        player.setDoT(0, 0);
        if (player.getHp() <= 0 && !playerDied) {
            player.died();
            kill = 1;
        }

        player.stun(1);

        if (kill == 1) {
            this.xp += max(0, 200 - (this.level - player.getLevel()) * 40);
        }
    }

    public void levelUp() {
        int oldLevel = this.level;
        super.levelUp();
        if (this.level > oldLevel) {
            this.hp = 900 + 80 * this.level;
        }
    }
}
