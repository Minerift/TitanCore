package org.avaeriandev.titancore.listeners;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent e) {

        Player plr = e.getPlayer();
        Block block = e.getBlock();

        RegionManager rm = WorldGuardPlugin.inst().getRegionManager(plr.getWorld());
        String[] woodRGList = {
                "ewood",
                "dwood",
                "cwood1",
                "cwood2",
                "bwood",
                "awood"
        };

        for(String regionName : woodRGList) {
            if(rm.getRegion(regionName).contains(block.getX(), block.getY(), block.getZ())) {
                // Convert all wood to standard oak wood
                if(block.getType() == Material.LOG || block.getType() == Material.LOG_2) {

                    block.setType(Material.AIR);
                    block.getLocation().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.LOG, 1));

                    // Cancel wood break event
                    e.setCancelled(true);
                    return;
                }

                // Convert all leaves to standard oak leaves
                if(block.getType() == Material.LEAVES || block.getType() == Material.LEAVES_2) {

                    block.setType(Material.AIR);

                    if(e.getPlayer().getItemInHand().getType() == Material.SHEARS) {
                        // Drop item
                        block.getLocation().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.LEAVES, 1));
                    }

                    // Cancel wood break event
                    e.setCancelled(true);
                    return;
                }
            }
        }

        // Fix tallgrass
        if(block.getType() == Material.LONG_GRASS) {
            e.setCancelled(true);
            return;
        }

        // Fix ice
        if(block.getType() == Material.ICE) {
            block.setType(Material.AIR);
            e.setCancelled(true);
            return;
        }
    }
}