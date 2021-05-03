package org.minerift.titan.modules.enchanter.util;

import org.avaeriandev.api.panel.IconScript;
import org.avaeriandev.api.util.BaseUtils;
import org.minerift.titan.TitanPlugin;
import org.minerift.titan.TitanProfile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;

public class EnchantScript implements IconScript, Listener {

    private EnchantLevel level;
    private InventoryView inventoryView;

    public EnchantScript(EnchantLevel level) {
        this.level = level;
    }

    @Override
    public void run(Player plr) {
        Bukkit.getPluginManager().registerEvents(this, TitanPlugin.getInstance());

        Location enchantmentTable = new Location(Bukkit.getWorld("Titan"), 557, 76, 1758);
        enchantmentTable.getChunk().load();

        this.inventoryView = plr.openEnchanting(enchantmentTable, true);
    }

    @EventHandler
    public void onPreEnchantItem(PrepareItemEnchantEvent e) {
        if(e.getView().equals(inventoryView)) {
            e.getExpLevelCostsOffered()[0] = level.getLevel(0);
            e.getExpLevelCostsOffered()[1] = level.getLevel(1);
            e.getExpLevelCostsOffered()[2] = level.getLevel(2);
        }
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent e) {
        if(e.getView().equals(inventoryView)) {
            Player plr = e.getEnchanter();
            TitanProfile titanProfile = TitanProfile.get(plr);

            if(titanProfile.getTickets() >= level.getLevel(e.whichButton())) {
                titanProfile.setTickets(titanProfile.getTickets() - level.getLevel(e.whichButton()));
            } else {
                plr.sendMessage(BaseUtils.chat("&cYou don't have enough tickets!"));
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if(e.getView().equals(inventoryView)) {
            HandlerList.unregisterAll(this);
        }
    }
}
