package com.leagueofoop.player;

public class Pyromancer extends Player {
    Pyromancer(int id, int xPos, int yPos) {
        super(id, xPos, yPos);
        this.hp = 500;
        this.type = PlayerType.Pyromancer;
    }

    public String getType() {
        return "P";
    }
}
