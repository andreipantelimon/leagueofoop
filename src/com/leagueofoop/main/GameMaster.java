package com.leagueofoop.main;

import com.leagueofoop.board.*;
import com.leagueofoop.player.Player;

import java.util.ArrayList;

public class GameMaster {
    ArrayList<Player> playersList = new ArrayList<>();
    Ground[][] board;
    private static GameMaster instance = null;

    private GameMaster(){}

    public static GameMaster getInstance() {
        if (instance == null) {
            instance = new GameMaster();
        }
        return instance;
    }

    public void playTheGame(GameInput gameInput) {
        initializeBoard(gameInput);
        for (int i = 0; i < gameInput.getRoundNumber(); i++) {
            String moveData = gameInput.getRoundData().get(i);
            movePlayers(moveData);
        }
    }

    private void initializeBoard(GameInput gameInput) {
        board = new Ground[gameInput.getXDim()][gameInput.getYDim()];
        for (int i = 0; i < gameInput.getXDim(); i++) {
            String tempData = gameInput.getGroundData().get(i);
            for (int j = 0; j < gameInput.getYDim(); j++) {
                char tempGround = tempData.charAt(j);
                if (tempGround == 'D') {
                    board[i][j] = new Desert();
                }
                if (tempGround == 'L') {
                    board[i][j] = new Land();
                }
                if (tempGround == 'V') {
                    board[i][j] = new Volcanic();
                }
                if (tempGround == 'W') {
                    board[i][j] = new Woods();
                }
            }
        }
    }

    private void movePlayers(String moveData) {
        for (int id = 0; id < moveData.length(); id++) {
            char move = moveData.charAt(id);
            switch (move) {
                case 'U': playersList.get(id).moveUp();
                break;
                case 'D': playersList.get(id).moveDown();
                break;
                case 'L': playersList.get(id).moveLeft();
                break;
                case 'R': playersList.get(id).moveRight();
                break;
            }
        }
    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    public void addPlayer(Player player) {
        playersList.add(player);
    }
}
