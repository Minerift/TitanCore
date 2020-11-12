package org.avaeriandev.titancore.listeners;

import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.TitanPlayerAPI;
import org.avaeriandev.titancore.auction.AuctionListing;
import org.avaeriandev.titancore.auction.AuctionSystem;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        OfflinePlayer plr = e.getPlayer();

        // Get rid of claimed auction listings
        for(AuctionListing listing : new ArrayList<>(AuctionSystem.listings)) {
            if(listing.getOwner() != null && listing.getOwner().equals(plr.getUniqueId())) {
                listing.getDeletionTimer().cancel();
                listing.delete();
            }
        }

        // Unload player
        TitanPlayerAPI.save(plr);
        TitanPlayerAPI.unload(plr);

    }

}
