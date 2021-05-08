package org.minerift.titan.modules.gemshop.offers.scripts;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MoneyOfferScript implements OfferScript {

    private int money;

    public MoneyOfferScript(int money) {
        this.money = money;
    }

    @Override
    public void run(Player plr) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + plr.getName() + " " + money);
    }
}
