package player;

import board.Ground;
import board.GroundType;
import main.Constants;

import java.io.IOException;

import static java.lang.Float.min;
import static java.lang.Integer.max;

public class Wizard extends Player {
    Wizard(final int id, final int xPos, final int yPos) {
        super(id, xPos, yPos);
        this.setHp(Constants.W_MAX_HP);
        this.setMaxHp(Constants.W_MAX_HP);
        this.setType(PlayerType.Wizard);
    }

    /**
     * Returns the Wizard type.
     * @return String
     */
    public String getType() {
        return "W";
    }


    /**
     * Accepts the fight.
     * @param player Any player
     * @param ground Any ground
     */
    public void accept(final Player player, final Ground ground) {
        player.fight(this, ground);
    }

    /**
     * {@inheritDoc}
     * @param player Any player.
     * @param ground Ground that he is on.
     */
    @Override
    final void fight(final Player player, final Ground ground) {
        int kill = 0;
        boolean playerDied = false;

        //Ground modifier.
        float groundModifier = 0;
        if (ground.getType().equals(GroundType.Desert)) {
            groundModifier = Constants.DESERT_MOD;
        }

        float drainPercent = Constants.DRAIN_BASE_DMG
                + Constants.DRAIN_LEVEL_DMG * this.getLevel();
        float baseHp = min(Constants.DRAIN_HP_MOD
                * player.getMaxHp(), player.getHp());

        float drainPercentAfterGround = drainPercent
                + (groundModifier * drainPercent);

        //Drain race modifier.
        float drainModifier = 0;
        switch (player.getType()) {
            case "K": drainModifier = Constants.W_DRAIN_K_MOD;
                break;
            case "P": drainModifier = Constants.W_DRAIN_P_MOD;
                break;
            case "R": drainModifier = Constants.W_DRAIN_R_MOD;
                break;
            case "W": drainModifier = Constants.W_DRAIN_W_MOD;
            default:
        }
        float drainPercentAfterRace;
        if (drainModifier != 0) {
            drainPercentAfterRace = drainPercentAfterGround
                    + ((drainModifier + strategyPercent + angelPercent) * drainPercentAfterGround);
        } else {
            drainPercentAfterRace = drainPercentAfterGround
                    + ((drainModifier + strategyPercent) * drainPercentAfterGround);
        }

        int drainDmg = Math.round(drainPercentAfterRace * baseHp);

        //Drain damage is calculated and applied.
        System.out.println(drainDmg);
        player.setHp(player.getHp() - drainDmg);
        if (player.getHp() <= 0) {
            player.died();
            kill = 1;
            playerDied = true;
        }

        //If the fighting player is Wizard, it skips him
        if (!player.getType().equals("W")) {
            float deflectPercent = Constants.DEFLECT_BASE_DMG
                    + Constants.DEFLECT_LEVEL_DMG * this.getLevel();
            deflectPercent = min(deflectPercent, Constants.DEFLECT_MOD);

            float deflectPercentAfterGround = deflectPercent
                    + (groundModifier * deflectPercent);

            //Deflect race modifier.
            float deflectModifier = 0;
            switch (player.getType()) {
                case "K": deflectModifier = Constants.W_DEFLECT_K_MOD;
                    break;
                case "P": deflectModifier = Constants.W_DEFLECT_P_MOD;
                    break;
                case "R": deflectModifier = Constants.W_DEFLECT_R_MOD;
                    break;
                default:
            }
            float deflectPercentAfterRace;
            if (deflectModifier != 0) {
                deflectPercentAfterRace = deflectPercentAfterGround
                        + ((deflectModifier + strategyPercent + angelPercent) * deflectPercentAfterGround);
            } else {
                deflectPercentAfterRace = deflectPercentAfterGround
                        + ((deflectModifier + strategyPercent) * deflectPercentAfterGround);
            }
            int deflectDmg = Math.round(deflectPercentAfterRace
                    * player.getDamageToWizard());

            //Deflect damage is calculated and applied.
            System.out.println(player.getDamageToWizard());
            player.setHp(player.getHp() - deflectDmg);
            if (player.getHp() <= 0 && !playerDied) {
                player.died();
                kill = 1;
            }
        }

        player.resetDamageToWizard();
        //Xp is added if necessary.
//        if (kill == 1) {
//            if (!this.isDead()) {
//                this.addXp(max(0, Constants.BASE_XP
//                        - (this.getLevel() - player.getLevel()) * Constants.LEVEL_XP));
//            }
//        }
    }

    //Level up function
    public final void levelUp() throws IOException {
        int oldLevel = this.getLevel();
        super.levelUp();
        if (this.getLevel() > oldLevel) {
            this.setMaxHp(Constants.W_MAX_HP
                    + Constants.W_LEVEL_HP * this.getLevel());
            this.setHp(this.getMaxHp());
        }
    }
}
