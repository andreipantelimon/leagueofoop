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
            //TODO: if 2 players didnt die after a fight, fight again next round if they dont move
        }
        for (int i = 0; i < gameInput.getXDim(); i++) {
            for (int j = 0; j < gameInput.getYDim(); j++) {
                System.out.println(board[i][j]);
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
            int tempX = player.getxPos();
            int tempY = player.getyPos();
            int tempId = player.getId();

            //TODO: nu prea e bine aici asta, trebuie pusa dupa runda

            if (board[tempX][tempY].isOccupied()) {
                int p1Id = board[tempX][tempY].getOccupantId();
                int p2Id = player.getId();

                //TODO: fight p1 and p2, winner is occupant
            } else {
                board[tempX][tempY].setOccupied(tempId);
            }
        }
    }

    private void movePlayers(String moveData) {
        for (int id = 0; id < moveData.length(); id++) {
            char move = moveData.charAt(id);
            Player tempPlayer = playersList.get(id);
            switch (move) {
                case 'U':
                    board[tempPlayer.getxPos()][tempPlayer.getyPos()].occupantLeft();
                    if (board[tempPlayer.getxPos() - 1][tempPlayer.getyPos()].isOccupied()) {
                        tempPlayer.moveUp();
                        //TODO: fight
                    } else {
                        board[tempPlayer.getxPos() - 1][tempPlayer.getyPos()].setOccupied(tempPlayer.getId());
                        tempPlayer.moveUp();
                    }
                    break;
                case 'D':
                    board[tempPlayer.getxPos()][tempPlayer.getyPos()].occupantLeft();
                    if (board[tempPlayer.getxPos() + 1][tempPlayer.getyPos()].isOccupied()) {
                        tempPlayer.moveDown();
                        //TODO: fight
                    } else {
                        board[tempPlayer.getxPos() + 1][tempPlayer.getyPos()].setOccupied(tempPlayer.getId());
                        tempPlayer.moveDown();
                    }
                    break;
                case 'L':
                    board[tempPlayer.getxPos()][tempPlayer.getyPos()].occupantLeft();
                    if (board[tempPlayer.getxPos()][tempPlayer.getyPos() - 1].isOccupied()) {
                        tempPlayer.moveLeft();
                        //TODO: fight
                    } else {
                        board[tempPlayer.getxPos()][tempPlayer.getyPos() - 1].setOccupied(tempPlayer.getId());
                        tempPlayer.moveLeft();
                    }
                    break;
                case 'R':
                    board[tempPlayer.getxPos()][tempPlayer.getyPos()].occupantLeft();
                    if (board[tempPlayer.getxPos()][tempPlayer.getyPos() + 1].isOccupied()) {
                        tempPlayer.moveRight();
                        //TODO: fight
                    } else {
                        board[tempPlayer.getxPos()][tempPlayer.getyPos() + 1].setOccupied(tempPlayer.getId());
                        tempPlayer.moveRight();
                    }
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
