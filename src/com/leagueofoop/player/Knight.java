package com.leagueofoop.player;

import com.leagueofoop.board.Ground;
import com.leagueofoop.board.GroundType;

import static java.lang.Integer.max;

public class Knight extends Player {
    Knight(int id, int xPos, int yPos) {
        super(id, xPos, yPos);
        this.hp = 900;
        this.type = PlayerType.Knight;
    }

    public String getType() {
        return "K";
    }

    public void accept(Player player, Ground ground) {
        player.fight(this, ground);
    }

    void fight(Player player, Ground ground) {
        System.out.println("Fight between " + this.getType() + " " + player.getType());

        int kill = 0;
        int executeDmg = 200 + 30 * this.level;
        int executeHpLimit = Math.round((20 + this.level) * player.getHp() / 100);
        if (((20 + this.level) * player.getHp()) > 40) {
            executeHpLimit = Math.round(40 * player.getHp() / 100);
        }
        int executeModifier = 0;
        switch (player.getType()) {
            case "K": executeModifier = 0;
            break;
            case "P": executeModifier = 10;
            break;
            case "R": executeModifier = 15;
            break;
            case "W": executeModifier = -20;
        }
        executeDmg += Math.round(executeModifier * executeDmg / 100);

        int groundModifier = 0;
        if (ground.getType().equals(GroundType.Land)) {
            groundModifier = 15;
        }
        executeDmg += Math.round(groundModifier * executeDmg / 100);

        if (player.getHp() <= executeHpLimit) {
            player.died();
            kill = 1;
        } else {
            player.setHp(player.getHp() - executeDmg);
        }

        int slamDmg = 100 + 40 * this.level;
        int slamModifier = 0;
        switch (player.getType()) {
            case "K": slamModifier = 20;
                break;
            case "P": slamModifier = -10;
                break;
            case "R": slamModifier = -20;
                break;
            case "W": slamModifier = 5;
        }
        slamDmg += Math.round(slamModifier * slamDmg / 100);
        slamDmg += Math.round(groundModifier * slamDmg / 100);

        player.setHp(player.getHp() - slamDmg);
        if (player.getHp() <= 0) {
            player.died();
            kill = 1;
        }

        player.stun();

        if (kill == 1) {
            this.xp += max(0, 200 - (this.level - player.getLevel()) * 40);
            //TODO: level up
        }
    }
}
