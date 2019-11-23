package com.leagueofoop.player;

public class Wizard extends Player {
    Wizard(int id, int xPos, int yPos) {
        super(id, xPos, yPos);
        this.hp = 400;
        this.type = PlayerType.Wizard;
    }

    public String getType() {
        return "W";
    }
}
