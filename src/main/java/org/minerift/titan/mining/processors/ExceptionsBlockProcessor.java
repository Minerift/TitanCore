package org.minerift.titan.mining.processors;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.minerift.titan.mining.BlockProcessor;

import java.util.ArrayList;
import java.util.List;

public class ExceptionsBlockProcessor implements BlockProcessor {

    // Handle wood, ice, etc.

    @Override
    public List<ItemStack> processOutput(BlockBreakEvent info, List<ItemStack> currentOutput) {

        if(info.getBlock().getType() == Material.ICE) {
            info.setCancelled(true);
            info.getBlock().setType(Material.AIR);

            // blockHandler.markComplete();

            return new ArrayList<>();
        }

        for(int i = 0; i < currentOutput.size(); i++) {
            ItemStack drop = currentOutput.get(i);

            switch(drop.getType()) {
                case LOG:
                case LOG_2:

                    // Cancel block break and handle
                    info.getBlock().setType(Material.AIR);
                    info.setCancelled(true);

                    // Set drops to oak wood for consistency
                    ItemStack logItem = new ItemStack(Material.LOG, 1);
                    currentOutput.set(i, logItem);
                    break;

            }
        }

        return currentOutput;

    }
}
