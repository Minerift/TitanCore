package org.minerift.titan.listeners;

import org.minerift.titan.TitanProfile;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        // Load player data
        Player plr = e.getPlayer();
        TitanProfile.load(plr);

        // Give player first-join bread (if eligible)
        if(!plr.hasPlayedBefore()) {
            plr.getInventory().addItem(new ItemStack(Material.BREAD, 5));
        }

    }

}
