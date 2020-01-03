package player;

import board.Ground;
import main.Constants;
import main.GameMaster;
import main.GreatMagician;

import java.io.IOException;
import java.util.Objects;

public abstract class Player implements PlayerObservable, PlayerVisitable {
    private int id;
    private int xPos;
    private int yPos;
    private int hp = -1;
    private int maxHp = 0;
    private int xp = 0;
    private int level = 0;
    private int damageOverTime = 0;
    private int damageOverTimeRounds = 0;
    private int damageToWizard = 0;
    private int stunnedRounds = 0;
    private boolean isDead = false;
    private boolean isStunned = false;
    private PlayerType type = null;
    private float strategyPercent = 0f;
    private float angelPercent = 0f;

    Player(final int id, final int xPos, final int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Overwritten in every player class for visitor pattern.
     */
    public void accept(final Player player, final Ground ground) {
        player.fight(this, ground);
    }

    /**
     * Fight function.
     * @param player Any player.
     * @param ground Ground that he is on.
     * @see Knight
     * @see Pyromancer
     * @see Wizard
     * @see Rogue
     */
    void fight(final Player player, final Ground ground) { }

    /**
     * Level Up function.
     */
    public void levelUp() throws IOException {
        while (this.xp >= (Constants.LEVELUP_BASE_XP
                + this.level * Constants.LEVELUP_LEVEL_XP)) {
            this.level++;
            this.notifyLevelUp();
        }
    }

    //Function that allows player to take damage over time.
    public final void takeDoT() {
        if (damageOverTimeRounds != 0) {
            this.hp -= damageOverTime;
            damageOverTimeRounds--;
        } else {
            damageOverTime = 0;
        }
    }

    final void setDoT(final int rounds, final int damage) {
        this.damageOverTimeRounds = rounds;
        this.damageOverTime = damage;
    }

    /**
     * Returns the type of every player.
     */
    public String getType() {
        return null;
    }

    public final void moveUp() {
        if (!isDead) {
            this.xPos--;
        }
    }

    public final void moveDown() {
        if (!isDead) {
            this.xPos++;
        }
    }

    public final void moveLeft() {
        if (!isDead) {
                this.yPos--;
            }
    }

    public final void moveRight() {
        if (!isDead) {
            this.yPos++;
        }
    }

    /**
     * Prints the player.
     * @return String
     */
    public String toString() {
        if (isDead) {
            return this.getType() + " dead";
        } else {
            return this.getType() + " " + this.level + " "
                    + this.xp + " " + this.hp + " " + this.xPos + " " + this.yPos;
        }
    }

    public final int getxPos() {
        return xPos;
    }

    public final int getyPos() {
        return yPos;
    }

    public final int getHp() {
        return this.hp;
    }

    public final void setHp(final int x) {
        this.hp = x;
    }

    public final void died(final Ground board) {
        this.isDead = true;
        board.setNumPlayers(board.getNumPlayers() - 1);
        board.addDeadPlayers(this);
    }

    public final void alive() {
        this.isDead = false;
        GameMaster.getInstance().getBoard()[this.xPos][this.yPos]
                .setNumPlayers(GameMaster.getInstance().getBoard()[this.xPos][this.yPos]
                        .getNumPlayers() + 1);
    }

    public final boolean isDead() {
        return this.isDead;
    }

    public final int getLevel() {
        return this.level;
    }

    final void stun(final int rounds) {
        this.stunnedRounds = rounds;
        this.isStunned = true;
    }

    public final boolean isStunned() {
        return this.isStunned;
    }

    public final void removeStun() {
        this.isStunned = false;
    }

    public final void decStun() {
        this.stunnedRounds--;
    }

    public final int getStunnedRounds() {
        return this.stunnedRounds;
    }

    public final int getMaxHp() {
        return this.maxHp;
    }

    final int getDamageToWizard() {
        return this.damageToWizard;
    }

    public final void resetDamageToWizard() {
        this.damageToWizard = 0;
    }

    final void setMaxHp(final int maxHp) {
        this.maxHp = maxHp;
    }

    public final void setDead(final boolean dead) {
        isDead = dead;
    }

    /**
     * Sets the type of player.
     * @param type Rogue, Pyro, Knight or Wizard
     */
    public void setType(final PlayerType type) {
        this.type = type;
    }

    final void addDamageToWizard(final int x) {
        this.damageToWizard += x;
    }

    public final void addXp(final int x) {
        this.xp += x;
    }

    public final void addStrategyPercent(final float percent) {
        this.strategyPercent += percent;
    }

    public final int getXp() {
        return this.xp;
    }

    public final void addAngelPercent(final float percent) {
        this.angelPercent += percent;
    }

    public final int getId() {
        return this.id;
    }

    @Override
    public final void notifyLevelUp() throws IOException {
        GreatMagician.getInstance().updateLevelUp(this);
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return id == player.id
                && xPos == player.xPos
                && yPos == player.yPos
                && hp == player.hp
                && maxHp == player.maxHp
                && xp == player.xp
                && level == player.level
                && isDead == player.isDead;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, xPos, yPos, hp, maxHp, xp, level, isDead);
    }

    public final float getStrategyPercent() {
        return strategyPercent;
    }

    public final float getAngelPercent() {
        return angelPercent;
    }
}
