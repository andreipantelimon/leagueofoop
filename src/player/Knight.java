package player;

import board.Ground;
import board.GroundType;
import main.Constants;

import static java.lang.Integer.max;

public class Knight extends Player {
    Knight(final int id, final int xPos, final int yPos) {
        super(id, xPos, yPos);
        this.setHp(Constants.K_MAX_HP);
        this.setMaxHp(Constants.K_MAX_HP);
        this.setType(PlayerType.Knight);
    }

    /**
     * Returns the Knight type.
     * @return String
     */
    public String getType() {
        return "K";
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
        float executeDmg = Constants.EXECUTE_BASE_DMG
                + Constants.EXECUTE_LEVEL_DMG * this.getLevel();

        //Execute hp limit is calculated.
        int executeHpLimit = Math.round((Constants.EXECUTE_HP_LIMIT
                + this.getLevel()) * player.getHp()
                / Constants.PERCENT_DIVISOR);

        if (((Constants.EXECUTE_HP_LIMIT + this.getLevel())
                * player.getHp()) > Constants.EXECUTE_HP_MAX) {
            executeHpLimit = Math.round(Constants.EXECUTE_HP_MAX
                    * player.getHp()
                    / Constants.PERCENT_DIVISOR);
        }

        //Ground modifier
        float groundModifier = 0;
        if (ground.getType().equals(GroundType.Land)) {
            groundModifier = Constants.LAND_MOD;
        }

        //Execute race modifier.
        float executeModifier = 0;
        switch (player.getType()) {
            case "K": executeModifier = Constants.K_EXECUTE_K_MOD;
            break;
            case "P": executeModifier = Constants.K_EXECUTE_P_MOD;
            break;
            case "R": executeModifier = Constants.K_EXECUTE_R_MOD;
            break;
            case "W": executeModifier = Constants.K_EXECUTE_W_MOD;
            default:
        }
        float executeDmgAfterGround = executeDmg
                + (groundModifier * executeDmg);

        //Damage for the wizard if it is in battle him.
        this.addDamageToWizard(Math.round(executeDmgAfterGround));

        int executeDmgAfterRace = Math.round(executeDmgAfterGround
                + executeModifier * executeDmgAfterGround);

        // Final execute damage is calculated and applied.
        if (player.getHp() <= executeHpLimit) {
            int executeDmgAll = player.getHp();
            player.setHp(player.getHp() - executeDmgAll);
            player.died();
            kill = 1;
            playerDied = true;
        } else {
            player.setHp(player.getHp() - executeDmgAfterRace);
        }

        float slamDmg = Constants.SLAM_BASE_DMG
                + Constants.SLAM_LEVEL_DMG * this.getLevel();
        float slamModifier = 0;

        float slamDmgAfterGround = slamDmg + (groundModifier * slamDmg);

        //Damage for the wizard if it is in battle him.
        this.addDamageToWizard(Math.round(slamDmgAfterGround));

        //Slam race modifier.
        switch (player.getType()) {
            case "K": slamModifier = Constants.K_SLAM_K_MOD;
                break;
            case "P": slamModifier = Constants.K_SLAM_P_MOD;
                break;
            case "R": slamModifier = Constants.K_SLAM_R_MOD;
                break;
            case "W": slamModifier = Constants.K_SLAM_W_MOD;
            default:
        }
        int slamDmgAfterRace = Math.round(slamDmgAfterGround
                + slamModifier * slamDmgAfterGround);

        //Slam damage is calculated and applied.
        player.setHp(player.getHp() - slamDmgAfterRace);
        player.setDoT(0, 0);
        if (player.getHp() <= 0 && !playerDied) {
            player.died();
            kill = 1;
        }

        // Player is stunned.
        player.stun(1);

        //Adds the xp if target is killed.
        if (kill == 1) {
            this.addXp(max(0, Constants.BASE_XP - (this.getLevel()
                    - player.getLevel()) * Constants.LEVEL_XP));
        }
    }

    // Level up function.
    public final void levelUp() {
        int oldLevel = this.getLevel();
        super.levelUp();
        if (this.getLevel() > oldLevel) {
            this.setHp(Constants.K_MAX_HP
                    + Constants.K_LEVEL_HP * this.getLevel());
        }
    }
}
