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
import java.util.Iterator;
import java.util.Map;

import static java.lang.Integer.max;

//Singleton pattern.

public final class GameMaster implements GameObservable {
    private ArrayList<Player> playersList = new ArrayList<>();
    private Ground[][] board;
    private static GameMaster instance = null;
    private HashMap<Integer, ArrayList<Angel>> angelsMap = new HashMap<>();
    private GameMaster() { }

    public static GameMaster getInstance() {
        if (instance == null) {
            instance = new GameMaster();
        }
        return instance;
    }

    void playTheGame(final GameInput gameInput, final GameIOLoader gameIOLoader) throws IOException {
        AngelFactory angelFactory = new AngelFactory();
        initializeAngelsMap(gameInput);
        initializeBoard(gameInput);
        initializePlayersOnBoard();
        for (int i = 0; i < gameInput.getRoundNumber(); i++) {
            System.out.println("\n");
            GameIOLoader.write("~~ Round " + (i+1) + " ~~");

            String moveData = gameInput.getRoundData().get(i);
            System.out.println("Aiciiii: " + angelsMap);
            addAngels(gameInput, i, angelFactory);
            System.out.println("Aiciiii: " + angelsMap);

            for (Player player : playersList) {
                player.resetDamageToWizard();
                // Player takes over time damage and dies if necessary.
                player.takeDoT();
                if (player.getHp() <= 0 && !player.isDead()) {
                    player.died(board[player.getxPos()][player.getyPos()]);
                }
            }
            movePlayers(moveData);

            //The players fight, if one of them is Wizard he attacks second.
            System.out.println("*** Inceput runda: " + i);
            for (Player player : playersList) {
                System.out.println(player);
            }
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
                                if (p2.isDead()) {
                                    notifyDead(p1, p2);
                                }
                                if (p1.isDead()) {
                                    notifyDead(p2, p1);
                                }
                            }
                            if (p2.getType().equals("W") && !fightOk) {
                                p2.accept(p1, board[xDim][yDim]);
                                p1.accept(p2, board[xDim][yDim]);
                                fightOk = true;
                                if (p2.isDead()) {
                                    notifyDead(p1, p2);
                                }
                                if (p1.isDead()) {
                                    notifyDead(p2, p1);
                                }
                            }
                            if (!fightOk) {
                                p1.accept(p2, board[xDim][yDim]);
                                p2.accept(p1, board[xDim][yDim]);
                                if (p2.isDead()) {
                                    notifyDead(p1, p2);
                                }
                                if (p1.isDead()) {
                                    notifyDead(p2, p1);
                                }
                            }

                            if (!p1.isDead() && p2.isDead()) {
                                p1.addXp(max(0, Constants.BASE_XP - (p1.getLevel()
                                        - p2.getLevel()) * Constants.LEVEL_XP));
                            } else {
                                if (p1.isDead() && !p2.isDead()) {
                                    p2.addXp(max(0, Constants.BASE_XP - (p2.getLevel()
                                            - p1.getLevel()) * Constants.LEVEL_XP));
                                }
                            }
                        }
                    }

                    for (Player player : playersList) {
                        if (!player.isDead()) {
                            player.levelUp();
                        }
                    }
                }
            }
            ArrayList<Angel> angelsListToIterate = angelsMap.get(i);
            for (Angel angel : angelsListToIterate) {
                angel.notifySpawn();
                int xDim = angel.getxPos();
                int yDim = angel.getyPos();
                if (angel instanceof Spawner) {
                    for (Player dead : board[xDim][yDim].getDeadPlayers()) {
                        dead.acceptAngel(angel);
                    }
                    board[xDim][yDim].getDeadPlayers().clear();
                } else {
                    boolean visit = false;
                    if (board[xDim][yDim].getNumPlayers() == 2) {
                        Player p1 = board[xDim][yDim].getPlayer1();
                        Player p2 = board[xDim][yDim].getPlayer2();
                        p1.acceptAngel(angel);
                        p2.acceptAngel(angel);
                        visit = true;
                    }
                    if (board[xDim][yDim].getNumPlayers() == 1 && !visit) {
                        Player player;
                        if (board[xDim][yDim].getPlayer1() != null) {
                            if (!board[xDim][yDim].getPlayer1().isDead()) {
                                player = board[xDim][yDim].getPlayer1();
                            } else {
                                player = board[xDim][yDim].getPlayer2();
                            }
                        } else {
                            player = board[xDim][yDim].getPlayer2();
                        }
                        if (player != null) {
                            player.acceptAngel(angel);
                        }
                    }
                }
            }
            for (Player player : playersList) {
                if (!player.isDead()) {
                    player.levelUp();
                }
            }

            System.out.println("*** Final runda: ");
            for (Player player : playersList) {
                System.out.println(player);
            }
            GameIOLoader.write("");
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
    }

    //Players with new / old coordinates are put on board.
    private void initializePlayersOnBoard() {
        for (Player player : playersList) {
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

    private void reInitializePlayersOnBoard() {
        for (Player player : playersList) {
            if (!player.isDead()) {
                if (player.getxPos() >= 0 && player.getyPos() >= 0) {
                    Ground tempGround = board[player.getxPos()][player.getyPos()];
                    if (tempGround.getNumPlayers() == 0) {
                        board[player.getxPos()][player.getyPos()].setPlayer1(player);
                    } else {
                        if (tempGround.getNumPlayers() == 1) {
                            if (tempGround.getPlayer1() != null) {
                                if (!tempGround.getPlayer1().isDead() && !tempGround.getPlayer1().equals(player)) {
                                    board[player.getxPos()][player.getyPos()].setPlayer2(player);
                                }
                            } else {
                                if (!tempGround.getPlayer2().equals(player)) {
                                    board[player.getxPos()][player.getyPos()].setPlayer1(player);
                                }
                            }
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
                    int oldXPos = tempPlayer.getxPos();
                    int oldYPos = tempPlayer.getyPos();
                    switch (move) {
                        case 'U':
                            board[oldXPos][oldYPos].removePlayer(tempPlayer);
                            tempPlayer.moveUp();
                            break;
                        case 'D':
                            board[oldXPos][oldYPos].removePlayer(tempPlayer);
                            tempPlayer.moveDown();
                            break;
                        case 'L':
                            board[oldXPos][oldYPos].removePlayer(tempPlayer);
                            tempPlayer.moveLeft();
                            break;
                        case 'R':
                            board[oldXPos][oldYPos].removePlayer(tempPlayer);
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
        reInitializePlayersOnBoard();
    }

    void addAngels(GameInput gameInput, int round, AngelFactory angelFactory) {
        int id = 0;
        ArrayList tempAngels = (ArrayList) gameInput.getAngelsMap().get(round);
        for (int i = 0; i < tempAngels.size(); i++) {
            String tempAngelString = (String) tempAngels.get(i);
            String[] angelParts = tempAngelString.split(",", 3);
            int tempXPos = Integer.parseInt(angelParts[1]);
            int tempYPos = Integer.parseInt(angelParts[2]);
            Angel tempAngel = angelFactory.createAngel(id, angelParts[0], tempXPos,  tempYPos);
            ArrayList<Angel> tempAngelArray = angelsMap.get(round);
            tempAngelArray.add(tempAngel);
            angelsMap.put(round, tempAngelArray);
            board[tempXPos][tempYPos].addAngel(tempAngel);
        }
    }

    void initializeAngelsMap(GameInput gameInput) {
        for (int i = 0; i < gameInput.getRoundNumber(); i++) {
            angelsMap.put(i, new ArrayList<>());
        }
    }

    ArrayList<Player> getPlayersList() {
        return playersList;
    }

    void addPlayer(final Player player) {
        playersList.add(player);
    }

    @Override
    public void notifyDead(Player p1, Player p2) throws IOException {
        GreatMagician.getInstance().updateDead(p1, p2);
    }

    public Ground[][] getBoard() {
        return this.board;
    }
}
