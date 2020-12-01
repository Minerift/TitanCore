package org.avaeriandev.titancore.christmas.giftrewards;

import de.tr7zw.nbtapi.NBTItem;
import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.api.util.ItemBuilder;
import org.avaeriandev.titancore.christmas.XmasItemEnum;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class XmasItemGiftReward extends GiftReward {

    private XmasItemEnum xmasItemEnum;
    private int minAmount, maxAmount;

    public XmasItemGiftReward(XmasItemEnum xmasItemEnum, int minAmount, int maxAmount) {
        this.xmasItemEnum = xmasItemEnum;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    @Override
    public void rewardPlayer(Player plr) {

        int amount = ThreadLocalRandom.current().nextInt(minAmount, maxAmount + 1);

        NBTItem nbtItem = new NBTItem(new ItemBuilder(new ItemStack(xmasItemEnum.getItem())).setAmount(amount).create());
        nbtItem.setString("XmasItem", xmasItemEnum.name());
        plr.getInventory().addItem(nbtItem.getItem());

        String name = ChatColor.stripColor(BaseUtils.chat(xmasItemEnum.getItem().getItemMeta().getDisplayName())).toLowerCase();
        if(amount > 1) name += "s";

        plr.sendMessage(BaseUtils.chat("&aYou found " + amount + " " + name + " from a gift!"));
        plr.playSound(plr.getLocation(), Sound.LEVEL_UP, 1, BaseUtils.randomFloat(1.5F, 2F));
    }
}
