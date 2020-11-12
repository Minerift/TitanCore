package org.avaeriandev.titancore.quest.rewards;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MoneyReward extends Reward {

    private int money;

    public MoneyReward(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public void rewardPlayer(Player plr) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + plr.getName() + " " + money);
    }
}
