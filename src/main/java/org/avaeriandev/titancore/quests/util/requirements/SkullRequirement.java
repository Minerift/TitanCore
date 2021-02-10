package org.avaeriandev.titancore.quests.util.requirements;

import de.tr7zw.nbtapi.NBTItem;
import org.avaeriandev.api.util.BaseUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SkullRequirement extends Requirement {

    private int amount;

    public SkullRequirement(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean meetsRequirement(Player plr) {

        int skullsCounted = 0;
        Inventory inventory = plr.getInventory();

        for(int i = 0; i < inventory.getSize(); i++) {

            ItemStack selectedItem = inventory.getItem(i);
            if(selectedItem == null) { continue; }
            
            NBTItem nbtItem = new NBTItem(selectedItem);
            if(nbtItem.hasKey("quest-skull")) { skullsCounted += selectedItem.getAmount(); }
        }

        return skullsCounted >= amount;
    }

    @Override
    public void removeRequirement(Player plr) {

        int skullsLeft = amount;
        Inventory inventory = plr.getInventory();

        for(int i = 0; i < inventory.getSize(); i++) {

            ItemStack selectedItem = inventory.getItem(i);
            if(selectedItem == null) { continue; }
            
            NBTItem nbtItem = new NBTItem(selectedItem);
            if(nbtItem.hasKey("quest-skull")) {
                if(selectedItem.getAmount() > skullsLeft) {
                    selectedItem.setAmount(selectedItem.getAmount() - skullsLeft);
                    break;
                } else if (selectedItem.getAmount() == skullsLeft) {
                    plr.getInventory().setItem(i, new ItemStack(Material.AIR));
                    break;
                } else if (selectedItem.getAmount() < skullsLeft) {
                    skullsLeft -= selectedItem.getAmount();
                    plr.getInventory().setItem(i, new ItemStack(Material.AIR));
                }
            }
        }

    }

    @Override
    public void remindPlayer(Player plr) {
        String message = "&2Bring me " + amount + " player head";
        if(amount > 1) message += "s";

        plr.sendMessage(BaseUtils.chat(message));
    }

    @Override
    public String getRequirementLore() {
        String message = "&7Bring me " + amount + " player head";
        if(amount > 1) message += "s";

        return message;
    }
}
