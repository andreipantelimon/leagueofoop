package player;

import board.Ground;

public abstract class Player {
    int id = -1;
    int xPos = -1;
    int yPos = -1;
    int hp = -1;
    int xp = 0;
    int level = 0;
    boolean isDead = false;
    boolean isStunned = false;
    PlayerType type = null;

    Player(int id, int xPos, int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void accept(Player player, Ground ground) {
        player.fight(this, ground);
    }

    void fight(Player player, Ground ground) {
        System.out.println("Abstract fight");
    }

    String getType() {
        return null;
    }

    public void moveUp() {
        if (!isDead) {
            this.xPos--;
        }
    }

    public void moveDown() {
        if (!isDead) {
            this.xPos++;
        }
    }

    public void moveLeft() {
        if (!isDead) {
            this.yPos--;
        }
    }

    public void moveRight() {
        if (!isDead) {
            this.yPos++;
        }
    }

    public String toString() {
        if (isDead) {
            return this.getType() + " dead";
        } else {
            return this.getType() + " " + this.level + " " + this.xp + " " + this.hp + " " + this.xPos + " " + this.yPos;
        }
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getId() {
        return this.id;
    }

    public int getHp() {
        return this.hp;
    }

    public void died() {
        this.isDead = true;
    }

    public void setHp(int x) {
        this.hp = x;
    }

    public boolean isDead() {
        return this.isDead;
    }

    public int getLevel() {
        return this.level;
    }

    public void stun() {
        this.isStunned = true;
    }

    public boolean isStunned() {
        return this.isStunned;
    }

    public void removeStun() {
        this.isStunned = false;
    }
}