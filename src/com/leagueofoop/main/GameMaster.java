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
            //TODO: Take DoT damage
            movePlayers(moveData);

            for (int xDim = 0; xDim < gameInput.getXDim(); xDim++) {
                for (int yDim = 0; yDim < gameInput.getYDim(); yDim++) {
                    if (board[xDim][yDim].getNumPlayers() == 2) {
                        Player p1 = board[xDim][yDim].getPlayer1();
                        Player p2 = board[xDim][yDim].getPlayer2();
                        p1.accept(p2, board[xDim][yDim]);
                        p2.accept(p1, board[xDim][yDim]);
                    }
                }
            }
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

        for (Player player : playersList) {
            Ground tempGround = board[player.getxPos()][player.getyPos()];
            if (tempGround.getNumPlayers() == 0) {
                board[player.getxPos()][player.getyPos()].setPlayer1(player);
            } else {
                board[player.getxPos()][player.getyPos()].setPlayer2(player);
            }
        }
    }

    private void movePlayers(String moveData) {
        for (int id = 0; id < moveData.length(); id++) {
            char move = moveData.charAt(id);
            Player tempPlayer = playersList.get(id);
            if (!tempPlayer.isStunned()) {
                switch (move) {
                    case 'U':
                        tempPlayer.moveUp();
                        //TODO: reset board players
                        break;
                    case 'D':
                        tempPlayer.moveDown();
                        break;
                    case 'L':
                        tempPlayer.moveLeft();
                        break;
                    case 'R':
                        tempPlayer.moveRight();
                        break;
                }
            } else {
                tempPlayer.removeStun();
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
