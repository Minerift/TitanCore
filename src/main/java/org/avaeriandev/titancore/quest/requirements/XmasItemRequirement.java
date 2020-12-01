package org.avaeriandev.titancore.quest.requirements;

import de.tr7zw.nbtapi.NBTItem;
import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.titancore.christmas.XmasItemEnum;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class XmasItemRequirement extends Requirement {

    private XmasItemEnum xmasItem;

    private ItemStack item;
    private int amount;

    public XmasItemRequirement(XmasItemEnum xmasItem, int amount) {
        this.xmasItem = xmasItem;
        this.amount = amount;

        NBTItem nbtItem = new NBTItem(new ItemStack(xmasItem.getItem()));
        nbtItem.setString("XmasItem", xmasItem.name());
        this.item = nbtItem.getItem();
    }

    @Override
    public boolean meetsRequirement(Player plr) {
        return plr.getInventory().containsAtLeast(item, amount);
    }

    @Override
    public void removeRequirement(Player plr) {
        int amountRemaining = amount;
        int currentSlotIndex = 0;

        while(true) {

            ItemStack currentSlotItem = plr.getInventory().getItem(currentSlotIndex);
            if(currentSlotItem != null && currentSlotItem.isSimilar(item)) {
                if(currentSlotItem.getAmount() > amountRemaining) {
                    currentSlotItem.setAmount(currentSlotItem.getAmount() - amountRemaining);
                    break;
                } else if (currentSlotItem.getAmount() == amountRemaining) {
                    plr.getInventory().setItem(currentSlotIndex, new ItemStack(Material.AIR));
                    break;
                } else if (currentSlotItem.getAmount() < amountRemaining) {
                    amountRemaining -= currentSlotItem.getAmount();
                    plr.getInventory().setItem(currentSlotIndex, new ItemStack(Material.AIR));
                }
            }
            currentSlotIndex++;
        }

        plr.updateInventory();
    }

    @Override
    public void remindPlayer(Player plr) {

        String message = "&2Bring me " + amount + " " + ChatColor.stripColor(BaseUtils.chat(item.getItemMeta().getDisplayName()));
        if(amount > 1) message += "s";

        plr.sendMessage(BaseUtils.chat(message));
    }

    @Override
    public String getRequirementLore() {

        String lore = "&7Bring me " + amount + " " + ChatColor.stripColor(BaseUtils.chat(item.getItemMeta().getDisplayName()));
        if(amount > 1) lore += "s";

        return lore;
    }
}
