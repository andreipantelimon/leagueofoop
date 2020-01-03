package strategy;

import main.Constants;
import player.Player;

//Strategy pattern implementations based on player's hp.

public class ConcreteStrategy implements Strategy {
    public final void applyStrategy(final Player player) {
        if (Constants.getStrategyEmpowerMin(player.getType()) * player.getMaxHp() <  player.getHp()
                && player.getHp() < Constants.getStrategyEmpowerMax(player.getType())
                * player.getMaxHp()) {
            empowerStrategy(player);
        } else {
            if (player.getHp() < Constants.getStrategyHealMax(player.getType())
                    * player.getMaxHp()) {
                healStrategy(player);
            }
        }
    }
    private void empowerStrategy(final Player player) {
        player.setHp(player.getHp() - (int) (player.getHp()
                * Constants.getStrategyEmpowerHp(player.getType())));
        player.addStrategyPercent(Constants.getStrategyEmpowerPercent(player.getType()));
    }

    private void healStrategy(final Player player) {
        player.addStrategyPercent(-Constants.getStrategyHealPercent(player.getType()));
        player.setHp(player.getHp() + (int) (player.getHp()
                * Constants.getStrategyHealHp(player.getType())));
    }
}
