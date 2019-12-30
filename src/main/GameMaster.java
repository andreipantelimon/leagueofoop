package main;

import angel.Angel;
import angel.AngelFactory;
import angel.Spawner;
import board.Desert;
import board.Ground;
import board.Land;
import board.Volcanic;
import board.Woods;
import player.Player;
import strategy.ConcreteStrategy;
import strategy.Strategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//Singleton pattern.

final class GameMaster {
    private ArrayList<Player> playersList = new ArrayList<>();
    private HashMap<Integer, Angel> angelsMap = new HashMap<>();
    private Ground[][] board;
    private static GameMaster instance = null;

    private GameMaster() { }

    static GameMaster getInstance() {
        if (instance == null) {
            instance = new GameMaster();
        }
        return instance;
    }

    void playTheGame(final GameInput gameInput, final GameIOLoader gameIOLoader) throws IOException {
        initializeBoard(gameInput);
        for (int i = 0; i < gameInput.getRoundNumber(); i++) {
            System.out.println("\n");
            gameIOLoader.write("~~ Round " + (i+1) + " ~~");

            String moveData = gameInput.getRoundData().get(i);

            addAngels(gameInput, i);

            for (Player player : playersList) {
                player.resetDamageToWizard();
                // Player takes over time damage and dies if necessary.
                player.takeDoT();
                if (player.getHp() <= 0) {
                    player.died();
                }
            }

            movePlayers(moveData);
            reinitializeGround(gameInput);
            initializePlayersOnBoard();

            //The players fight, if one of them is Wizard he attacks second.
            for (int xDim = 0; xDim < gameInput.getXDim(); xDim++) {
                for (int yDim = 0; yDim < gameInput.getYDim(); yDim++) {
                    System.out.println("*** Angels: ");
                    System.out.println(board[xDim][yDim].getAngelList());
                    //TODO: Notify observers
                    System.out.println("*** Inceput runda: ");
                    for (Player player : playersList) {
                        System.out.println(player);
                    }

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

                    if (board[xDim][yDim].getNumPlayers() == 2) {
                        Player p1 = board[xDim][yDim].getPlayer1();
                        Player p2 = board[xDim][yDim].getPlayer2();
                        for (Angel angel : board[xDim][yDim].getAngelList()) {
                            if (angel instanceof Spawner) {
                                angel.visitPlayer(p1);
                                angel.visitPlayer(p2);
                            } else {
                                if (!p1.isDead() && !p2.isDead()) {
                                    angel.visitPlayer(p1);
                                    angel.visitPlayer(p2);
                                }
                                if (p1.isDead() && !p2.isDead()) {
                                    angel.visitPlayer(p2);
                                }
                                if (!p1.isDead() && p2.isDead()) {
                                    angel.visitPlayer(p1);
                                }
                            }
                        }
                    }

                    if (board[xDim][yDim].getNumPlayers() == 1) {
                        Player player = null;
                        if (board[xDim][yDim].getPlayer1() != null) {
                            player = board[xDim][yDim].getPlayer1();
                        } else {
                            if (board[xDim][yDim].getPlayer2() != null) {
                                player = board[xDim][yDim].getPlayer2();
                            }
                        }

                        assert player != null;

                        for (Angel angel : board[xDim][yDim].getAngelList()) {
                            if (angel instanceof Spawner) {
                                angel.visitPlayer(player);
                            } else {
                                if (!player.isDead()) {
                                    angel.visitPlayer(player);
                                }
                            }
                        }
                    }

                    board[xDim][yDim].getAngelList().clear();
                }
            }
            for (Player player : playersList) {
                if (!player.isDead()) {
                    player.levelUp();
                }
            }

            //gameIOLoader.write("\n");
            System.out.println("*** Final runda: ");
            for (Player player : playersList) {
                System.out.println(player);
            }
        }
    }

    //Playing board is declared.
    private void initializeBoard(final GameInput gameInput) {
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

    //Playing board is cleared.
    private void reinitializeGround(final GameInput gameInput) {
        for (int i = 0; i < gameInput.getXDim(); i++) {
            for (int j = 0; j < gameInput.getYDim(); j++) {
                board[i][j].setPlayer1(null);
                board[i][j].setPlayer2(null);
                board[i][j].setNumPlayers(0);
            }
        }
    }

    //Players with new / old coordinates are put on board.
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

    //Players are moved and stun is calculated.
    private void movePlayers(final String moveData) {
        for (int id = 0; id < moveData.length(); id++) {
            char move = moveData.charAt(id);
            Player tempPlayer = playersList.get(id);
            if (!tempPlayer.isDead()) {
                if (!tempPlayer.isStunned()) {
                    Strategy strategy = new ConcreteStrategy();
                    strategy.applyStrategy(tempPlayer);
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
                        default:
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

    void addAngels(GameInput gameInput, int round) {
        int id = 0;
        System.out.println("Round: " + round);
        ArrayList tempAngels = (ArrayList) gameInput.getAngelsMap().get(round);
        for (int i = 0; i < tempAngels.size(); i++) {
            String tempAngelString = (String) tempAngels.get(i);
            String[] angelParts = tempAngelString.split(",", 3);
            int tempXPos = Integer.parseInt(angelParts[1]);
            int tempYPos = Integer.parseInt(angelParts[2]);
            board[tempXPos][tempYPos].addAngel(AngelFactory.createAngel(id, angelParts[0], tempXPos,  tempYPos));
            //System.out.println("Angel " + angelParts[0] + " was spawned at " + tempXPos + " " + tempYPos);

        }
    }

    ArrayList<Player> getPlayersList() {
        return playersList;
    }

    void addPlayer(final Player player) {
        playersList.add(player);
    }
}
