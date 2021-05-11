package org.minerift.titan.mining;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface DropProcessor {

    public void handleDrops(BlockBreakEvent info, List<ItemStack> output);

}
