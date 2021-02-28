package org.avaeriandev.titancore.modules.tools.handlers;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import me.clip.ezblocks.EZBlocks;
import org.avaeriandev.titancore.MagnetHandler;
import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.TitanPlayerAPI;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ExplosiveHandler extends AbstractHandler {

    private final int radius = 3;
    private final int maxBlocksCounted = 6;

    @Override
    public boolean breakBlock(Player plr, ItemStack tool, Block block) {

        RegionManager rm = WorldGuardPlugin.inst().getRegionManager(block.getWorld());
        Location initialLocation = block.getLocation().clone().subtract(1, 1, 1);

        TitanPlayer titanPlayer = TitanPlayerAPI.get(plr);
        MagnetHandler magnetHandler = new MagnetHandler(titanPlayer) {
            @Override
            protected void useDefaultHandler(Block block, List<ItemStack> customDrops, boolean countForGems) {

                block.breakNaturally(tool);

                if(countForGems) {
                    EZBlocks.getEZBlocks().getBreakHandler().handleBlockBreakEvent(plr, block);
                }
            }
        };

        int blocksCounted = 0;

        for(int x = 0; x < radius; x++) {
            for(int y = 0; y < radius; y++) {
                for(int z = 0; z < radius; z++) {

                    Location location = initialLocation.clone().add(x, y, z);
                    ApplicableRegionSet set = rm.getApplicableRegions(location);
                    if(set.allows(DefaultFlag.BLOCK_BREAK) && set.allows(DefaultFlag.OTHER_EXPLOSION)) {

                        // Check if block is an actual block
                        if(location.getBlock().getType() != Material.AIR) {
                            magnetHandler.handleMagnet(location.getBlock(), tool, maxBlocksCounted >= blocksCounted);
                        }

                        blocksCounted++;
                    }
                }
            }
        }

        block.getWorld().spigot().playEffect(block.getLocation(), Effect.EXPLOSION_LARGE, 0, 0, 0, 0, 0, 1, 3, 30);

        return true;
    }
}
