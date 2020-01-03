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

    void playTheGame(final GameInput gameInput) throws IOException {
        AngelFactory angelFactory = new AngelFactory();
        initializeAngelsMap(gameInput);
        initializeBoard(gameInput);
        initializePlayersOnBoard();
        for (int i = 0; i < gameInput.getRoundNumber(); i++) {
            GameIOLoader.write("~~ Round " + (i + 1) + " ~~");

            String moveData = gameInput.getRoundData().get(i);
            addAngels(gameInput, i, angelFactory);

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
            for (Player playerFight : playersList) {
                int xDim = playerFight.getxPos();
                int yDim = playerFight.getyPos();
                if (xDim < 0 || yDim < 0) {
                    continue;
                }
                    boolean fightOk = false;
                    if (board[xDim][yDim].getNumPlayers() == 2
                            && !board[xDim][yDim].isFightCheck()) {
                        Player p1 = board[xDim][yDim].getPlayer1();
                        Player p2 = board[xDim][yDim].getPlayer2();
                        if (!p1.isDead() && !p2.isDead()) {
                            if (p1.getType().equals("W")) {
                                p1.accept(p2, board[xDim][yDim]);
                                p2.accept(p1, board[xDim][yDim]);
                                board[xDim][yDim].setFightCheck(true);
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
                                board[xDim][yDim].setFightCheck(true);
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
                                board[xDim][yDim].setFightCheck(true);
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
            //Angels visit the players.
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

            GameIOLoader.write("");

            for (int xDim = 0; xDim < gameInput.getXDim(); xDim++) {
                for (int yDim = 0; yDim < gameInput.getYDim(); yDim++) {
                    board[xDim][yDim].setFightCheck(false);
                }
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
    }

    //Players are initialized on board.
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

    //Players are reinitialized on board after they moved.
    private void reInitializePlayersOnBoard() {
        for (Player player : playersList) {
            if (!player.isDead()) {
                if (player.getxPos() >= 0 && player.getyPos() >= 0) {
                    Ground tempGround = board[player.getxPos()][player.getyPos()];
                    if (tempGround.getNumPlayers() == 0) {
                        board[player.getxPos()][player.getyPos()].setPlayer1(player);
                    } else {
                        if (tempGround.getNumPlayers() == 1) {
                            boolean ok = true;
                            if (tempGround.getPlayer1() != null) {
                                if (!tempGround.getPlayer1().isDead()
                                        && !tempGround.getPlayer1().equals(player)) {
                                    board[player.getxPos()][player.getyPos()].setPlayer2(player);
                                } else {
                                    if (tempGround.getPlayer2() != null) {
                                        if (tempGround.getPlayer2().equals(player)) {
                                            ok = false;
                                        }
                                    }
                                    if (tempGround.getPlayer1().isDead()
                                            && !tempGround.getPlayer1().equals(player) && ok) {
                                        board[player.getxPos()][player.getyPos()]
                                                .setPlayer1(player);
                                    }
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
                            if (oldXPos >= 0 && oldYPos >= 0) {
                                board[oldXPos][oldYPos].removePlayer(tempPlayer);
                            }
                            tempPlayer.moveUp();
                            break;
                        case 'D':
                            if (oldXPos >= 0 && oldYPos >= 0) {
                                board[oldXPos][oldYPos].removePlayer(tempPlayer);
                            }
                            tempPlayer.moveDown();
                            break;
                        case 'L':
                            if (oldXPos >= 0 && oldYPos >= 0) {
                                board[oldXPos][oldYPos].removePlayer(tempPlayer);
                            }
                            tempPlayer.moveLeft();
                            break;
                        case 'R':
                            if (oldXPos >= 0 && oldYPos >= 0) {
                                board[oldXPos][oldYPos].removePlayer(tempPlayer);
                            }
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

    //Angels are added to board and to a HashMap.
    void addAngels(final GameInput gameInput, final int round, final AngelFactory angelFactory) {
        int id = 0;
        ArrayList tempAngels = (ArrayList) gameInput.getAngelsMap().get(round);
        for (int i = 0; i < tempAngels.size(); i++) {
            String tempAngelString = (String) tempAngels.get(i);
            String[] angelParts = tempAngelString.split(",", Constants.SPLIT_LIMIT);
            int tempXPos = Integer.parseInt(angelParts[1]);
            int tempYPos = Integer.parseInt(angelParts[2]);
            Angel tempAngel = angelFactory.createAngel(id, angelParts[0], tempXPos,  tempYPos);
            ArrayList<Angel> tempAngelArray = angelsMap.get(round);
            tempAngelArray.add(tempAngel);
            angelsMap.put(round, tempAngelArray);
            board[tempXPos][tempYPos].addAngel(tempAngel);
        }
    }

    void initializeAngelsMap(final GameInput gameInput) {
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
    public void notifyDead(final Player p1, final Player p2) throws IOException {
        GreatMagician.getInstance().updateDead(p1, p2);
    }

    public Ground[][] getBoard() {
        return this.board;
    }
}
