package com.leagueofoop.player;

public class Knight extends Player {
    Knight(int id, int xPos, int yPos) {
        super(id, xPos, yPos);
        this.hp = 900;
        this.type = PlayerType.Knight;
    }

    public String getType() {
        return "K";
    }
}
