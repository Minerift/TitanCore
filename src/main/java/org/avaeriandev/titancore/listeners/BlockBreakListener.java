package org.avaeriandev.titancore.listeners;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import de.tr7zw.nbtapi.NBTItem;
import net.lightshard.prisoncells.PrisonCells;
import net.lightshard.prisoncells.cell.PrisonCell;
import org.avaeriandev.titancore.tools.CustomToolEnum;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class BlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent e) {

        Player plr = e.getPlayer();
        Block block = e.getBlock();

        RegionManager rm = WorldGuardPlugin.inst().getRegionManager(block.getWorld());
        ApplicableRegionSet set = rm.getApplicableRegions(block.getLocation());

        String[] woodRGList = {
                "ewood",
                "dwood",
                "cwood1",
                "cwood2",
                "bwood",
                "awood"
        };

        // Determine if custom tool was used
        if(plr.getItemInHand() != null && plr.getItemInHand().getType() != Material.AIR) {
            ItemStack toolUsed = plr.getItemInHand();
            NBTItem nbtItem = new NBTItem(toolUsed);

            CustomToolEnum customToolEnum = null;
            try {

                customToolEnum = CustomToolEnum.valueOf(nbtItem.getString("ToolType").toUpperCase());
                if(set.allows(DefaultFlag.BLOCK_BREAK)) {

                    PrisonCell cell = PrisonCells.getInstance().getCellManager().getByLocation(block.getLocation());
                    if(cell != null) return;

                    boolean cancelEvent = customToolEnum.getHandler().breakBlock(plr, toolUsed, block);
                    e.setCancelled(cancelEvent);
                }

            } catch(IllegalArgumentException ex) {}
        }

        if(set.allows(DefaultFlag.BLOCK_BREAK)) {

            // Handle wood regions
            for(ProtectedRegion region : set.getRegions()) {
                if(Arrays.asList(woodRGList).contains(region.getId())) {

                    switch(block.getType()) {

                        case WOOD:
                        case WOOD_STEP:
                        case TRAP_DOOR:
                        case FENCE:
                        case WOODEN_DOOR:
                        case WOOD_STAIRS:
                            return;

                        case LOG:
                        case LOG_2:
                            block.setType(Material.AIR);
                            block.getLocation().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.LOG, 1));

                            // Cancel wood break event
                            e.setCancelled(true);
                            return;

                        case LEAVES:
                        case LEAVES_2:
                            block.setType(Material.AIR);

                            if(e.getPlayer().getItemInHand().getType() == Material.SHEARS) {
                                // Drop item
                                block.getLocation().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.LEAVES, 1));
                            }

                            // Cancel wood break event
                            e.setCancelled(true);
                            return;

                        default:
                            e.setCancelled(true);
                            return;
                    }
                }
            }

            // Handle everything else accordingly
            switch(block.getType()) {
                case LONG_GRASS:
                    e.setCancelled(true);
                    return;

                case ICE:
                    e.setCancelled(true);
                    block.setType(Material.AIR);
                    return;
            }
        }
    }
}