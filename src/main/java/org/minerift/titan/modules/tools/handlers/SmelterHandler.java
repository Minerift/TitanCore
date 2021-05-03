package org.minerift.titan.modules.tools.handlers;

import me.clip.ezblocks.EZBlocks;
import org.minerift.titan.modules.tools.MagnetHandler;
import org.minerift.titan.TitanProfile;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmelterHandler extends AbstractHandler {

    private Map<Material, Material> smelterMap;

    public SmelterHandler() {
        smelterMap = new HashMap<>();

        smelterMap.put(Material.IRON_ORE, Material.IRON_INGOT);
        smelterMap.put(Material.GOLD_ORE, Material.GOLD_INGOT);
        smelterMap.put(Material.STONE, Material.STONE);
    }

    @Override
    public boolean breakBlock(Player plr, ItemStack tool, Block block) {

        TitanProfile titanProfile = TitanProfile.get(plr);
        MagnetHandler magnetHandler = new MagnetHandler(titanProfile) {
            @Override
            protected void useDefaultHandler(Block block, List<ItemStack> customDrops, boolean countForGems) {
                block.setType(Material.AIR);

                for(ItemStack drop : customDrops) {
                    block.getLocation().getWorld().dropItemNaturally(block.getLocation(), drop);
                }

                if(countForGems) {
                    EZBlocks.getEZBlocks().getBreakHandler().handleBlockBreakEvent(plr, block);
                }
            }
        };

        if(smelterMap.containsKey(block.getType())) {

            // Get smelted block drop
            Material material = block.getType();
            ItemStack smeltedItem = new ItemStack(smelterMap.get(material));

            // Handle magnet
            magnetHandler.handleMagnet(block, Arrays.asList(smeltedItem), false);

        } else {

            // Handle magnet
            magnetHandler.handleMagnet(block, tool, false);

        }

        return true; // cancel event
    }
}
