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
        //System.out.println("Fight between " + this.getType() + " " + player.getType());

        int kill = 0;
        boolean playerDied = false;

        float groundModifier = 0;
        if (ground.getType().equals(GroundType.Volcanic)) {
            groundModifier = 0.25f;
        }

        float fireblastDmg = 350 + 50 * this.level;

        float fireblastDmgAfterGround = fireblastDmg + (groundModifier * fireblastDmg);

        this.damageToWizard += Math.round(fireblastDmgAfterGround);

        float fireblastModifier = 0;
        switch (player.getType()) {
            case "K": fireblastModifier = 0.2f;
                break;
            case "P": fireblastModifier = -0.1f;
                break;
            case "R": fireblastModifier = -0.2f;
                break;
            case "W": fireblastModifier = 0.05f;
        }

        int fireblastDmgAfterRace = Math.round(fireblastDmgAfterGround + (fireblastModifier * fireblastDmgAfterGround));

        player.setHp(player.getHp() - fireblastDmgAfterRace);

        if (player.getHp() <= 0) {
            player.died();
            kill = 1;
            playerDied = true;
        }

        float igniteDmg = 150 + 20 * this.level;
        float ignitePerRound = 50 + 30 * this.level;

        float igniteDmgAfterGround = igniteDmg + (groundModifier * igniteDmg);
        float ignitePerRoundAfterGround = ignitePerRound + (groundModifier * ignitePerRound);

        this.damageToWizard += Math.round(igniteDmgAfterGround);

        float igniteModifier = 0;
        switch (player.getType()) {
            case "K": igniteModifier = 0.2f;
                break;
            case "P": igniteModifier = -0.1f;
                break;
            case "R": igniteModifier = -0.2f;
                break;
            case "W": igniteModifier = 0.05f;
        }

        int igniteDmgAfterRace = Math.round(igniteDmgAfterGround + (igniteModifier * igniteDmgAfterGround));
        int ignitePerRoundAfterRace = Math.round(ignitePerRoundAfterGround + igniteModifier * ignitePerRoundAfterGround);

        player.setHp(player.getHp() - igniteDmgAfterRace);

        player.setDoT(2, ignitePerRoundAfterRace);

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
            this.hp = 500 + 50 * this.level;
        }
    }
}
