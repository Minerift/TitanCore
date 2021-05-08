package org.minerift.titan.modules.gemshop.offers.scripts;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemOfferScript implements OfferScript {

    private ItemStack item;

    public ItemOfferScript(ItemStack item) {
        this.item = item;
    }


    @Override
    public void run(Player plr) {

        plr.getInventory().addItem(item);

    }
}
