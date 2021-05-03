package org.minerift.titan.modules.gemshop;

import org.bukkit.inventory.ItemStack;

public enum GemShopOffer {
    ;

    private ItemStack icon;
    private int cost;

    private GemShopOffer(ItemStack icon, int cost) {
        this.icon = icon;
        this.cost = cost;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public int getCost() {
        return cost;
    }
}
