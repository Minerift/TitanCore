package org.avaeriandev.titancore.tools.handlers;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import me.clip.ezblocks.EZBlocks;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ExplosiveHandler extends AbstractHandler {

    private int radius = 3;

    @Override
    public boolean breakBlock(Player plr, ItemStack tool, Block block) {

        RegionManager rm = WorldGuardPlugin.inst().getRegionManager(block.getWorld());

        Location initialLocation = block.getLocation().clone().subtract(1, 1, 1);

        for(int x = 0; x < radius; x++) {
            for(int y = 0; y < radius; y++) {
                for(int z = 0; z < radius; z++) {

                    Location location = initialLocation.clone().add(x, y, z);
                    ApplicableRegionSet set = rm.getApplicableRegions(location);
                    if(set.allows(DefaultFlag.BLOCK_BREAK) && set.allows(DefaultFlag.OTHER_EXPLOSION)) {

                        // Add all non-air blocks to EZBlocks
                        if(location.getBlock().getType() != Material.AIR) {
                            EZBlocks.getEZBlocks().getBreakHandler().handleBlockBreakEvent(plr, location.getBlock());
                        }

                        location.getBlock().breakNaturally(tool);
                    }
                }
            }
        }

        return true;
    }
}
