package org.avaeriandev.titancore.listeners;

import org.avaeriandev.titancore.TitanPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        OfflinePlayer plr = e.getPlayer();
        TitanPlayer.unload(plr);

    }

}
