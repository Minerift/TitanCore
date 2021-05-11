package org.minerift.titan.mining;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.minerift.titan.mining.processors.ExceptionsBlockProcessor;
import org.minerift.titan.mining.processors.ToolBlockProcessor;

import java.util.ArrayList;
import java.util.List;

public class BlockHandler {

    // Block Info
    private List<ItemStack> output;
    private BlockBreakEvent info;

    // Processors
    private BlockProcessor[] blockProcessors;

    public BlockHandler(BlockBreakEvent event) {

        this.output = new ArrayList<>(event.getBlock().getDrops(info.getPlayer().getItemInHand()));
        this.info = event;

        this.blockProcessors = new BlockProcessor[]{
            new ExceptionsBlockProcessor(),
            new ToolBlockProcessor(),
        };

    }

    public void handle() {
        for(BlockProcessor processor : blockProcessors) {
            output = processor.processOutput(info, output);
        }

        // handle dropping items/whatever
    }

}
