package org.avaeriandev.titancore.listeners;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import de.tr7zw.nbtapi.NBTItem;
import net.lightshard.prisoncells.PrisonCells;
import net.lightshard.prisoncells.cell.PrisonCell;
import org.avaeriandev.api.util.WorldGuardUtils;
import org.avaeriandev.titancore.MagnetHandler;
import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.TitanPlayerAPI;
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
import java.util.List;

public class BlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
    public void onBlockBreak(BlockBreakEvent e) {

        Player plr = e.getPlayer();
        TitanPlayer titanPlayer = TitanPlayerAPI.get(plr);
        Block block = e.getBlock();

        RegionManager rm = WorldGuardPlugin.inst().getRegionManager(block.getWorld());
        ApplicableRegionSet set = rm.getApplicableRegions(block.getLocation());

        MagnetHandler magnetHandler = new MagnetHandler(titanPlayer) {
            @Override
            protected void useDefaultHandler(Block block, List<ItemStack> customDrops, boolean countForGems) {
                // Do nothing; let Minecraft handle
            }
        };

        String[] woodRGList = {
                "ewood",
                "dwood",
                "cwood1",
                "cwood2",
                "bwood",
                "awood"
        };

        // Handle custom tool
        if(plr.getItemInHand() != null && plr.getItemInHand().getType() != Material.AIR) {
            ItemStack toolUsed = plr.getItemInHand();
            NBTItem nbtItem = new NBTItem(toolUsed);

            CustomToolEnum customToolEnum = null;
            try {

                customToolEnum = CustomToolEnum.valueOf(nbtItem.getString("ToolType").toUpperCase());
                if(set.allows(DefaultFlag.BLOCK_BREAK)) {

                    if(WorldGuardUtils.isInCell(block.getLocation())) { return; }

                    boolean cancelEvent = customToolEnum.getHandler().breakBlock(plr, toolUsed, block);
                    e.setCancelled(cancelEvent);
                    return;
                }

            } catch(IllegalArgumentException ex) {}
        }

        // Handle wood mine blocks
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

                            ItemStack logItem = new ItemStack(Material.LOG, 1);
                            magnetHandler.handleMagnet(block, Arrays.asList(logItem), false); // Minecraft already handles gems

                            // Cancel wood break event
                            e.setCancelled(true);
                            return;

                        case LEAVES:
                        case LEAVES_2:
                            block.setType(Material.AIR);

                            if(e.getPlayer().getItemInHand().getType() == Material.SHEARS) {
                                // Drop item
                                ItemStack leavesItem = new ItemStack(Material.LEAVES, 1);
                                magnetHandler.handleMagnet(block, Arrays.asList(leavesItem), false); // Minecraft already handles gems
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

        // Handle regular blocks
        if(set.allows(DefaultFlag.BLOCK_BREAK) && !WorldGuardUtils.isInCell(block.getLocation())) {
            magnetHandler.handleMagnet(block, plr.getItemInHand(), false); // Already counted, no need to custom handle
        }
    }
}