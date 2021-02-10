package org.avaeriandev.titancore.tools.handlers;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import me.clip.ezblocks.EZBlocks;
import org.avaeriandev.titancore.MagnetHandler;
import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.TitanPlayerAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BountifulHandler extends AbstractHandler {

    private int radius;
    private Map<Material, Integer> materialPriorities;

    // Lower Number = Lower Priority (worse)
    // Higher Number = Higher Priority (better)

    public BountifulHandler() {
        radius = 3;
        materialPriorities = new HashMap<>();

        materialPriorities.put(Material.REDSTONE,       0);
        materialPriorities.put(Material.COAL_ORE,       1);
        materialPriorities.put(Material.COAL_BLOCK,     2);
        materialPriorities.put(Material.LAPIS_ORE,      3);
        materialPriorities.put(Material.LAPIS_BLOCK,    4);
        materialPriorities.put(Material.IRON_ORE,       5);
        materialPriorities.put(Material.GOLD_ORE,       6);
        materialPriorities.put(Material.IRON_BLOCK,     7);
        materialPriorities.put(Material.DIAMOND_ORE,    8);
        materialPriorities.put(Material.GOLD_BLOCK,     9);
        materialPriorities.put(Material.EMERALD_ORE,    10);
        materialPriorities.put(Material.DIAMOND_BLOCK,  11);
        materialPriorities.put(Material.EMERALD_BLOCK,  12);
    }

    @Override
    public boolean breakBlock(Player plr, ItemStack tool, Block block) {

        List<Block> surroundingBlocks = new ArrayList<>();
        Location initialLocation = block.getLocation().clone().subtract(1, 1, 1);
        RegionManager rm = WorldGuardPlugin.inst().getRegionManager(block.getWorld());

        TitanPlayer titanPlayer = TitanPlayerAPI.get(plr);
        MagnetHandler magnetHandler = new MagnetHandler(titanPlayer) {
            @Override
            protected void useDefaultHandler(Block block, List<ItemStack> customDrops, boolean countForGems) {
                block.setType(Material.AIR);

                World world = block.getWorld();
                for(ItemStack drop : customDrops) {
                    world.dropItemNaturally(block.getLocation(), drop);
                }

                if(countForGems) {
                    EZBlocks.getEZBlocks().getBreakHandler().handleBlockBreakEvent(plr, block);
                }
            }
        };

        for(int x = 0; x < radius; x++) {
            for(int y = 0; y < radius; y++) {
                for(int z = 0; z < radius; z++) {

                    Location location = initialLocation.clone().add(x, y, z);
                    ApplicableRegionSet set = rm.getApplicableRegions(location);
                    if(set.allows(DefaultFlag.BLOCK_BREAK) && set.allows(DefaultFlag.OTHER_EXPLOSION)) {
                        surroundingBlocks.add(location.getBlock());
                    }
                }
            }
        }

        // Get best block
        Block bestBlock = null;
        for(Block selectedBlock : surroundingBlocks) {
            if(materialPriorities.containsKey(selectedBlock.getType())) {
                int priority = materialPriorities.get(selectedBlock.getType());
                if(bestBlock == null || priority > materialPriorities.get(bestBlock.getType())) {
                    bestBlock = selectedBlock;
                }
            }
        }

        // Drop best material found
        Collection<ItemStack> drops = bestBlock != null ? bestBlock.getDrops(tool) : block.getDrops(tool);
        magnetHandler.handleMagnet(block, new ArrayList<>(drops), true);

        return true;
    }
}
