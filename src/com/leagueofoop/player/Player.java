package com.leagueofoop.player;

public abstract class Player {
    int id = -1;
    int xPos = -1;
    int yPos = -1;
    int hp = -1;
    int xp = 0;
    int level = 0;
    boolean isDead = false;
    boolean inFight = false;
    PlayerType type = null;

    Player(int id, int xPos, int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
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
}
