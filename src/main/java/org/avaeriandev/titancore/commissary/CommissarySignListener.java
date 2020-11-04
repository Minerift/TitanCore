package org.avaeriandev.titancore.commissary;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CommissarySignListener implements Listener {

    @EventHandler
    public void onSignClick(PlayerInteractEvent e) {

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK
                && e.getClickedBlock().getType() == Material.WALL_SIGN) {

            Block sign = e.getClickedBlock();
        }
    }
}
