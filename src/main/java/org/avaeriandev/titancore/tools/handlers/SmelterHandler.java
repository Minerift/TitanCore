package org.avaeriandev.titancore.tools.handlers;

import me.clip.ezblocks.EZBlocks;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
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

        if(smelterMap.containsKey(block.getType())) {

            Material material = block.getType();
            block.setType(Material.AIR);
            block.getLocation().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(smelterMap.get(material)));
            EZBlocks.getEZBlocks().getBreakHandler().handleBlockBreakEvent(plr, block);

            return true; // cancel event
        }

        return false;
    }
}
