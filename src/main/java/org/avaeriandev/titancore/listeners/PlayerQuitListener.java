package org.avaeriandev.titancore.listeners;

import org.avaeriandev.titancore.TitanAPI;
import org.avaeriandev.titancore.TitanPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        Player plr = e.getPlayer();

        TitanPlayer titanPlayer = TitanAPI.getTitanPlayer(plr);

        titanPlayer.save();
        titanPlayer.unregister();

    }

}
