package org.avaeriandev.titancore.quest.rewards;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemReward extends Reward {

    private ItemStack item;

    public ItemReward(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    public void rewardPlayer(Player plr) {
        plr.getInventory().addItem(item);
    }
}
