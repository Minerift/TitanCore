package org.minerift.titan.listeners;

import org.minerift.titan.TitanProfile;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        OfflinePlayer plr = e.getPlayer();
        TitanProfile.unload(plr);

    }

}
