package org.avaeriandev.titancore.quest.requirements;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemRequirement extends Requirement {

    private ItemStack item;
    private int count;

    public ItemRequirement(ItemStack item, int count) {
        this.item = item;
        this.count = count;
    }

    @Override
    public boolean meetsRequirement(Player plr) {
        return plr.getInventory().contains(item, count);
    }

    @Override
    public void removeRequirement(Player plr) {
        int amountRemaining = count;
        int currentSlotIndex = 0;

        while(true) {

            ItemStack currentSlotItem = plr.getInventory().getItem(currentSlotIndex);
            if(currentSlotItem.isSimilar(item)) {
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
}
