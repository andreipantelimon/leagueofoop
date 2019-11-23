package com.leagueofoop.player;

public class Rogue extends Player {
    Rogue(int id, int xPos, int yPos) {
        super(id, xPos, yPos);
        this.hp = 600;
        this.type = PlayerType.Rogue;
    }

    public String getType() {
        return "R";
    }
}
