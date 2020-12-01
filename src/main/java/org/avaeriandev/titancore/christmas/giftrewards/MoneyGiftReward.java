package org.avaeriandev.titancore.christmas.giftrewards;

import org.avaeriandev.api.util.BaseUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MoneyGiftReward extends GiftReward {

    private int money;

    public MoneyGiftReward(int money) {
        this.money = money;
    }

    @Override
    public void rewardPlayer(Player plr) {

        plr.sendMessage(BaseUtils.chat("&aYou found $" + money + " from a gift!"));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + plr.getName() + " " + money);
        plr.playSound(plr.getLocation(), Sound.LEVEL_UP, 1, BaseUtils.randomFloat(1.5F, 2F));

    }
}
