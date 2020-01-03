package player;

import angel.AngelVisitor;
import board.Ground;
import board.GroundType;
import main.Constants;

import java.io.IOException;

public class Rogue extends Player {
    private int hitCounter = 2;
    Rogue(final int id, final int xPos, final int yPos) {
        super(id, xPos, yPos);
        this.setHp(Constants.R_MAX_HP);
        this.setMaxHp(Constants.R_MAX_HP);
        this.setType(PlayerType.Rogue);
    }

    /**
     * Returns the Rogue type.
     * @return String
     */
    public String getType() {
        return "R";
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
        boolean crit = false;
        boolean playerDied = false;

        float backstabDmg = Constants.BACKSTAB_BASE_DMG
                + Constants.BACKSTAB_LEVEL_DMG * this.getLevel();

        // Crit is applied if necessary.
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

        if (crit) {
            backstabDmg = Constants.ROGUE_CRIT * backstabDmg;
        }

        //Ground modifier.
        float groundModifier = 0;
        if (ground.getType().equals(GroundType.Woods)) {
            groundModifier = Constants.WOODS_MOD;
        }

        int backstabDmgAfterCrit = Math.round(backstabDmg + (groundModifier * backstabDmg));

        //Damage for the wizard if it is in battle him.
        this.addDamageToWizard(backstabDmgAfterCrit);

        //Backstab race modifier.
        float backstabModifier = 0;
        switch (player.getType()) {
            case "K": backstabModifier = Constants.R_BACKSTAB_K_MOD;
                break;
            case "P":
            case "W":
                backstabModifier = Constants.R_BACKSTAB_PW_MOD;
                break;
            case "R": backstabModifier = Constants.R_BACKSTAB_R_MOD;
                break;
            default:
        }

        int backstabDmgAfterRace;
        if (backstabModifier != 0) {
            backstabModifier -= Constants.FLOAT_ERROR;
            backstabDmgAfterRace = Math.round(backstabDmgAfterCrit
                    + ((backstabModifier + getStrategyPercent()
                    + getAngelPercent()) * backstabDmgAfterCrit));
        } else {
            backstabDmgAfterRace = Math.round(backstabDmgAfterCrit
                    + ((backstabModifier + getStrategyPercent()) * backstabDmgAfterCrit));
        }

        //Backstab damage is calculated and applied.
        player.setHp(player.getHp() - backstabDmgAfterRace);
        if (player.getHp() <= 0) {
            player.died(ground);
            playerDied = true;
        }

        float paralysisDmg = Constants.PARALYSIS_BASE_DMG
                + Constants.PARALYSIS_LEVEL_DMG * this.getLevel();
        float paralysisModifier = 0;

        int paralysisDmgAfterGround = Math.round(paralysisDmg + (groundModifier * paralysisDmg));

        //Damage for the wizard if it is in battle him.
        this.addDamageToWizard(paralysisDmgAfterGround);

        switch (player.getType()) {
            case "K": paralysisModifier = Constants.R_PARALYSIS_K_MOD;
                break;
            case "P": paralysisModifier = Constants.R_PARALYSIS_P_MOD;
                break;
            case "W":
                paralysisModifier = Constants.R_PARALYSIS_W_MOD;
                break;
            case "R": paralysisModifier = Constants.R_PARALYSIS_R_MOD;
                break;
            default:
        }

        int paralysisDmgAfterRace;
        if (paralysisModifier != 0) {
            paralysisModifier -= Constants.FLOAT_ERROR;
            paralysisDmgAfterRace = Math.round(paralysisDmgAfterGround
                    + ((paralysisModifier + getStrategyPercent() + getAngelPercent())
                    * paralysisDmgAfterGround));
        } else {
            paralysisDmgAfterRace = Math.round(paralysisDmgAfterGround
                    + ((paralysisModifier + getStrategyPercent()) * paralysisDmgAfterGround));
        }

        int roundNumber = Constants.R_PARALYSIS_BASE_ROUND;
        if (ground.getType().equals(GroundType.Woods)) {
            roundNumber = Constants.R_PARALYSIS_EXT_ROUND;
        }
        player.setDoT(roundNumber, paralysisDmgAfterRace);
        player.stun(roundNumber);

        //Paralysis damage and stun is applied.

        player.setHp(player.getHp() - paralysisDmgAfterRace);

        if (player.getHp() <= 0 && !playerDied) {
            player.died(ground);
        }
    }

    //Level up function.
    public final void levelUp() throws IOException {
        int oldLevel = this.getLevel();
        super.levelUp();
        if (this.getLevel() > oldLevel) {
            this.setMaxHp(Constants.R_MAX_HP
                    + Constants.R_LEVEL_HP * this.getLevel());
            this.setHp(this.getMaxHp());
        }
    }

    @Override
    public final void acceptAngel(final AngelVisitor angel) throws IOException {
        angel.visitPlayer(this);
    }
}
