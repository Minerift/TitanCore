package org.avaeriandev.titancore.listeners;

import org.avaeriandev.titancore.TitanAPI;
import org.avaeriandev.titancore.TitanPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player plr = e.getPlayer();

        // Load player data
        TitanPlayer.load(plr);

        // Give player first-join bread (if eligible)
        if(!plr.hasPlayedBefore()) {
            plr.getInventory().addItem(new ItemStack(Material.BREAD, 5));
        }

    }

}
