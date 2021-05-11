package org.minerift.titan.mining;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface BlockProcessor {

    public List<ItemStack> processOutput(BlockBreakEvent info, List<ItemStack> currentOutput);

}
