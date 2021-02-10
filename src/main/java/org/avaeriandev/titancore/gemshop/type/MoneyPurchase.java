package org.avaeriandev.titancore.gemshop.type;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MoneyPurchase implements PurchaseType {

    private int amount;

    public MoneyPurchase(int amount) {
        this.amount = amount;
    }

    @Override
    public void purchase(Player plr) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + plr.getName() + " " + amount);
    }
}
