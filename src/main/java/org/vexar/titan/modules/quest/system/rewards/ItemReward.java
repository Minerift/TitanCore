package org.vexar.titan.modules.quest.system.rewards;

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

    @Override
    public String getRewardLore() {
        return "&8+ &d" + item.getType().name().toLowerCase().replaceAll("_", " ");
    }
}
