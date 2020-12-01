package org.avaeriandev.titancore.christmas.giftrewards;

import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.api.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class CoalGiftReward extends GiftReward {

    private ItemStack item;

    public CoalGiftReward(int minAmount, int maxAmount) {
        this.item = new ItemStack(Material.COAL, ThreadLocalRandom.current().nextInt(minAmount, maxAmount + 1));
    }

    @Override
    public void rewardPlayer(Player plr) {

        plr.getInventory().addItem(item);
        plr.sendMessage(BaseUtils.chat("&cYou found coal in a gift :("));
        plr.playSound(plr.getLocation(), Sound.VILLAGER_NO, 1, BaseUtils.randomFloat(0.8F, 1F));

    }
}
