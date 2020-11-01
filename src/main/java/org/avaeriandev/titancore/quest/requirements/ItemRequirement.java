package org.avaeriandev.titancore.quest.requirements;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemRequirement extends Requirement {

    private ItemStack item;

    public ItemRequirement(ItemStack item) {
        this.item = item;
    }

    @Override
    public boolean meetsRequirement(Player plr) {
        return plr.getInventory().contains(item, item.getAmount());
    }

    @Override
    public void removeRequirement(Player plr) {
        int amountRemaining = item.getAmount();
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
