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

        int scanAmount = 0;

        for(int i = 0; i < plr.getInventory().getSize(); i++) {
            ItemStack selectedItem = plr.getInventory().getItem(i);
            if(selectedItem != null && selectedItem.getType() != Material.AIR) {

                NBTItem nbtItem = new NBTItem(selectedItem);
                if(nbtItem.hasKey("XmasItem") && nbtItem.getString("XmasItem").equalsIgnoreCase(xmasItem.name())) {
                    scanAmount += selectedItem.getAmount();
                }
            }
        }

        return scanAmount >= amount;
    }

    @Override
    public void removeRequirement(Player plr) {
        
        int amountRemaining = amount;
        
        for(int i = 0; i < plr.getInventory().getSize(); i++) {
            
            ItemStack selectedItem = plr.getInventory().getItem(i);
            if(selectedItem != null && selectedItem.getType() != Material.AIR) {

                // Verify that item is a christmas event item
                NBTItem nbtItem = new NBTItem(selectedItem);
                if(nbtItem.hasKey("XmasItem") && nbtItem.getString("XmasItem").equalsIgnoreCase(xmasItem.name())) {
                    
                    // Remove amount needed
                    if(selectedItem.getAmount() > amountRemaining) {
                        selectedItem.setAmount(selectedItem.getAmount() - amountRemaining);
                        break;
                    } else if (selectedItem.getAmount() == amountRemaining) {
                        plr.getInventory().setItem(i, new ItemStack(Material.AIR));
                        break;
                    } else if (selectedItem.getAmount() < amountRemaining) {
                        amountRemaining -= selectedItem.getAmount();
                        plr.getInventory().setItem(i, new ItemStack(Material.AIR));
                    }
                    
                }
            }
            
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
