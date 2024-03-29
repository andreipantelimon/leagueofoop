package player;

import angel.AngelVisitor;
import board.Ground;
import board.GroundType;
import main.Constants;

import java.io.IOException;

public class Pyromancer extends Player {
    Pyromancer(final int id, final int xPos, final int yPos) {
        super(id, xPos, yPos);
        this.setHp(Constants.P_MAX_HP);
        this.setMaxHp(Constants.P_MAX_HP);
        this.setType(PlayerType.Pyromancer);
    }

    /**
     * Returns the type of Pyromancer.
     * @return String
     */
    public String getType() {
        return "P";
    }

    /**
     * Acceps the fight.
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

        // Ground modifier
        float groundModifier = 0;
        if (ground.getType().equals(GroundType.Volcanic)) {
            groundModifier = Constants.VOLCANIC_MOD;
        }

        float fireblastDmg = Constants.FIREBLAST_BASE_DMG
                + Constants.FIREBLAST_LEVEL_DMG * this.getLevel();

        int fireblastDmgAfterGround = Math.round(fireblastDmg + (groundModifier * fireblastDmg));

        //Damage for the wizard if it is in battle him.
        this.addDamageToWizard(fireblastDmgAfterGround);

        // Fireblast race modifier.
        float fireblastModifier = 0;
        switch (player.getType()) {
            case "K": fireblastModifier = Constants.P_FIREBLAST_K_MOD;
                break;
            case "P": fireblastModifier = Constants.P_FIREBLAST_P_MOD;
                break;
            case "R": fireblastModifier = Constants.P_FIREBLAST_R_MOD;
                break;
            case "W": fireblastModifier = Constants.P_FIREBLAST_W_MOD;
            default:
        }

        int fireblastDmgAfterRace;
        if (fireblastModifier != 0) {
            fireblastModifier -= Constants.FLOAT_ERROR;
            fireblastDmgAfterRace = Math.round(fireblastDmgAfterGround
                    + ((fireblastModifier + getStrategyPercent() + getAngelPercent())
                    * fireblastDmgAfterGround));
        } else {
            fireblastDmgAfterRace = Math.round(fireblastDmgAfterGround
                    + ((fireblastModifier + getStrategyPercent()) * fireblastDmgAfterGround));
        }

        //Fireblast damage is calculated and applied.
        player.setHp(player.getHp() - fireblastDmgAfterRace);

        if (player.getHp() <= 0) {
            player.died(ground);
        }

        float igniteDmg = Constants.IGNITE_BASE_DMG
                + Constants.IGNITE_LEVEL_DMG * this.getLevel();
        float ignitePerRound = Constants.IGNITE_ROUND_BASE_DMG
                + Constants.IGNITE_ROUND_LEVEL_DMG * this.getLevel();

        int igniteDmgAfterGround = Math.round(igniteDmg + (groundModifier * igniteDmg));
        int ignitePerRoundAfterGround = Math.round(ignitePerRound
                + (groundModifier * ignitePerRound));

        //Damage for the wizard if it is in battle him.
        this.addDamageToWizard(igniteDmgAfterGround);

        //Ignite race modifier
        float igniteModifier = 0;
        switch (player.getType()) {
            case "K": igniteModifier = Constants.P_IGNITE_K_MOD;
                break;
            case "P": igniteModifier = Constants.P_IGNITE_P_MOD;
                break;
            case "R": igniteModifier = Constants.P_IGNITE_R_MOD;
                break;
            case "W": igniteModifier = Constants.P_IGNITE_W_MOD;
            default:
        }

        int igniteDmgAfterRace, ignitePerRoundAfterRace;
        if (igniteModifier != 0) {
            igniteDmgAfterRace = Math.round(igniteDmgAfterGround
                    + ((igniteModifier + getStrategyPercent()
                    + getAngelPercent()) * igniteDmgAfterGround));
            ignitePerRoundAfterRace = Math.round(ignitePerRoundAfterGround
                    + (igniteModifier + getStrategyPercent() + getAngelPercent())
                    * ignitePerRoundAfterGround);
        } else {
            igniteDmgAfterRace = Math.round(igniteDmgAfterGround
                    + ((igniteModifier + getStrategyPercent()) * igniteDmgAfterGround));
            ignitePerRoundAfterRace = Math.round(ignitePerRoundAfterGround
                    + (igniteModifier + getStrategyPercent()) * ignitePerRoundAfterGround);
        }

        //Ignite and Ignite per round damage is calculated and applied
        player.setHp(player.getHp() - igniteDmgAfterRace);

        //Set damage over time.
        player.setDoT(2, ignitePerRoundAfterRace);

        if (player.getHp() <= 0 && !player.isDead()) {
            player.died(ground);
        }
    }

    //Level up function.
    public final void levelUp() throws IOException {
        int oldLevel = this.getLevel();
        super.levelUp();
        if (this.getLevel() > oldLevel) {
            this.setMaxHp(Constants.P_MAX_HP
                    + Constants.P_LEVEL_HP * this.getLevel());
            this.setHp(this.getMaxHp());
        }
    }

    @Override
    public final void acceptAngel(final AngelVisitor angel) throws IOException {
        angel.visitPlayer(this);
    }
}
