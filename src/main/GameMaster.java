package main;

import board.*;
import player.*;

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
            for (Player player : playersList) {
                player.takeDoT();
                if (player.getHp() <= 0) {
                    player.died();
                }
            }
            movePlayers(moveData);
            reinitializeGround(gameInput);
            initializePlayersOnBoard();

            for (int xDim = 0; xDim < gameInput.getXDim(); xDim++) {
                for (int yDim = 0; yDim < gameInput.getYDim(); yDim++) {
                    boolean fightOk = false;
                    if (board[xDim][yDim].getNumPlayers() == 2) {
                        Player p1 = board[xDim][yDim].getPlayer1();
                        Player p2 = board[xDim][yDim].getPlayer2();
                        if (!p1.isDead() && !p2.isDead()) {
                            if (p1.getType().equals("W")) {
                                p1.accept(p2, board[xDim][yDim]);
                                p2.accept(p1, board[xDim][yDim]);
                                fightOk = true;
                            }
                            if (p2.getType().equals("W") && !fightOk) {
                                p2.accept(p1, board[xDim][yDim]);
                                p1.accept(p2, board[xDim][yDim]);
                                fightOk = true;
                            }
                            if (!fightOk) {
                                p1.accept(p2, board[xDim][yDim]);
                                p2.accept(p1, board[xDim][yDim]);
                            }
                        }
                    }
                }
            }
            System.out.println("Round: " + i);
            for (Player player : playersList) {
                if (!player.isDead()) {
                    player.levelUp();
                }
                System.out.println(player.print());
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
        initializePlayersOnBoard();
    }

    private void reinitializeGround(GameInput gameInput) {
        for (int i = 0; i < gameInput.getXDim(); i++) {
            for (int j = 0; j < gameInput.getYDim(); j++) {
                board[i][j].setPlayer1(null);
                board[i][j].setPlayer2(null);
                board[i][j].setNumPlayers(0);
            }
        }
    }

    private void initializePlayersOnBoard() {
        for (Player player : playersList) {
            if (!player.isDead()) {
                Ground tempGround = board[player.getxPos()][player.getyPos()];
                if (tempGround.getNumPlayers() == 0) {
                    board[player.getxPos()][player.getyPos()].setPlayer1(player);
                } else {
                    if (tempGround.getNumPlayers() == 1) {
                        if (tempGround.getPlayer1() != null) {
                            board[player.getxPos()][player.getyPos()].setPlayer2(player);
                        } else {
                            board[player.getxPos()][player.getyPos()].setPlayer1(player);
                        }
                    }
                }
            }
        }
    }

    private void movePlayers(String moveData) {
        for (int id = 0; id < moveData.length(); id++) {
            char move = moveData.charAt(id);
            Player tempPlayer = playersList.get(id);
            if (!tempPlayer.isDead()) {
                if (!tempPlayer.isStunned()) {
                    switch (move) {
                        case 'U':
                            tempPlayer.moveUp();
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
                    tempPlayer.decStun();
                    if (tempPlayer.getStunnedRounds() == 0) {
                        tempPlayer.removeStun();
                    }
                }
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
