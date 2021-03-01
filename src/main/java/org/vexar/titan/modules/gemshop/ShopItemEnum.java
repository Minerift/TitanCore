package org.vexar.titan.modules.gemshop;

import org.vexar.titan.modules.gemshop.type.MoneyPurchase;
import org.vexar.titan.modules.gemshop.type.PurchaseType;
import org.bukkit.inventory.ItemStack;

public enum ShopItemEnum {

    MONEY_100K(
        null,
        new MoneyPurchase(100000),
        1000
    ),
    MONEY_250K(
        null,
        new MoneyPurchase(250000),
        2500
    ),
    MONEY_500K(
        null,
        new MoneyPurchase(500000),
        5000
    ),


    ;

    private ItemStack icon;
    private PurchaseType purchaseType;
    private int cost;

    private ShopItemEnum(ItemStack icon, PurchaseType purchaseType, int cost) {
        this.icon = icon;
        this.purchaseType = purchaseType;
        this.cost = cost;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public PurchaseType getPurchaseScript() {
        return purchaseType;
    }

    public int getCost() {
        return cost;
    }
}
