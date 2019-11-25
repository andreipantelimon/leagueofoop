package player;

import board.Ground;
import board.GroundType;

import static java.lang.Integer.max;

public class Rogue extends Player {
    int hitCounter = 2;
    Rogue(int id, int xPos, int yPos) {
        super(id, xPos, yPos);
        this.hp = 600;
        this.maxHp = 600;
        this.type = PlayerType.Rogue;
    }

    public String getType() {
        return "R";
    }

    public void accept(Player player, Ground ground) {
        player.fight(this, ground);
    }

    void fight(Player player, Ground ground) {
        int kill = 0;
        boolean crit = false;
        boolean playerDied = false;

        float backstabDmg = 200 + 20 * this.level;

        if (hitCounter == 2) {
            if (ground.getType().equals(GroundType.Woods)) {
                crit = true;
                hitCounter = 1;
            } else {
                hitCounter = 0;
            }
        } else {
            hitCounter++;
        }

        float backstabDmgAfterCrit = backstabDmg;
        if (crit) {
            backstabDmgAfterCrit = 1.5f * backstabDmg;
        }

        float groundModifier = 0;
        if (ground.getType().equals(GroundType.Woods)) {
            groundModifier = 0.15f;
        }

        backstabDmgAfterCrit = backstabDmgAfterCrit + (groundModifier * backstabDmgAfterCrit);

        this.damageToWizard += Math.round(backstabDmgAfterCrit);

        float backstabModifier = 0;
        switch (player.getType()) {
            case "K": backstabModifier = -0.1f;
                break;
            case "P":
            case "W":
                backstabModifier = 0.25f;
                break;
            case "R": backstabModifier = 0.2f;
                break;
        }
        int backstabDmgAfterRace = Math.round(backstabDmgAfterCrit + (backstabModifier * backstabDmgAfterCrit));

        player.setHp(player.getHp() - backstabDmgAfterRace);
        if (player.getHp() <= 0) {
            player.died();
            kill = 1;
            playerDied = true;
        }

        float paralysisDmg = 40 + 10 * this.level;
        float paralysisModifier = 0;

        float paralysisDmgAfterGround = paralysisDmg + (groundModifier * paralysisDmg);

        this.damageToWizard += Math.round(paralysisDmgAfterGround);

        switch (player.getType()) {
            case "K": paralysisModifier = -0.2f;
                break;
            case "P": paralysisModifier = 0.2f;
                break;
            case "W":
                paralysisModifier = 0.25f;
                break;
            case "R": paralysisModifier = -0.1f;
                break;
        }

        int paralysisDmgAfterRace = Math.round(paralysisDmgAfterGround + (paralysisModifier * paralysisDmgAfterGround));

        int roundNumber = 3;
        if (ground.getType().equals(GroundType.Woods)) {
            roundNumber = 6;
        }
        player.setDoT(roundNumber, paralysisDmgAfterRace);
        player.stun(roundNumber);

        player.setHp(player.getHp() - paralysisDmgAfterRace);

        if (player.getHp() <= 0 && !playerDied) {
            player.died();
            kill = 1;
        }

        if (kill == 1) {
            this.xp += max(0, 200 - (this.level - player.getLevel()) * 40);
        }
    }

    public void levelUp() {
        int oldLevel = this.level;
        super.levelUp();
        if (this.level > oldLevel) {
            this.hp = 600 + 40 * this.level;
        }
    }
}
