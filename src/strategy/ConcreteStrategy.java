package strategy;

import player.Player;

public class ConcreteStrategy implements Strategy {
    public void applyStrategy(Player player) {
        if ((float) 1/3 * player.getMaxHp() < (float) player.getHp() && (float) player.getHp() < (float) 1/2 * player.getMaxHp()) {
            empowerStrategy(player);
        }
        if ((float) player.getHp() < (float) 1/3 * player.getMaxHp()) {
            healStrategy(player);
        }
    }
    private void empowerStrategy(Player player) {
        System.out.println("empower");
    }

    private void healStrategy(Player player) {
        System.out.println("heal");
    }
}
