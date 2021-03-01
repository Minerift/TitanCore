package org.vexar.titan.listeners;

import org.vexar.titan.VexarProfile;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        OfflinePlayer plr = e.getPlayer();
        VexarProfile.unload(plr);

    }

}
