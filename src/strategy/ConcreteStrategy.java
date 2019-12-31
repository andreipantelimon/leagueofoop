package strategy;

import main.Constants;
import player.Player;

public class ConcreteStrategy implements Strategy {
    public void applyStrategy(Player player) {
        if (Constants.getStrategyEmpowerMin(player.getType()) * player.getMaxHp() < (float) player.getHp() &&
                (float) player.getHp() < Constants.getStrategyEmpowerMax(player.getType()) * player.getMaxHp()) {
            empowerStrategy(player);
        }
        if ((float) player.getHp() < Constants.getStrategyHealMax(player.getType()) * player.getMaxHp()) {
            healStrategy(player);
        }
    }
    private void empowerStrategy(Player player) {
        System.out.println("empower");
        player.setHp(player.getHp() - (int)(player.getHp() * Constants.getStrategyEmpowerHp(player.getType())));
        player.addStrategyPercent(Constants.getStrategyEmpowerPercent(player.getType()));
    }

    private void healStrategy(Player player) {
        System.out.println("heal " + player.getType());
        player.addStrategyPercent(- Constants.getStrategyHealPercent(player.getType()));
        player.setHp(player.getHp() + (int)(player.getHp() * Constants.getStrategyHealHp(player.getType())));
    }
}
