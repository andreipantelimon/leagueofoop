package com.leagueofoop.player;

import com.leagueofoop.board.Ground;

public class Rogue extends Player {
    Rogue(int id, int xPos, int yPos) {
        super(id, xPos, yPos);
        this.hp = 600;
        this.type = PlayerType.Rogue;
    }

    public String getType() {
        return "R";
    }

    public void accept(Player player, Ground ground) {
        player.fight(this, ground);
    }

    void fight(Player player, Ground ground) {
        System.out.println("Fight between " + this.getType() + " " + player.getType());
    }
}
