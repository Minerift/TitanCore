package org.minerift.titan.mining.processors;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.minerift.titan.mining.BlockProcessor;
import org.minerift.titan.modules.tools.CustomToolEnum;

import java.util.List;

public class ToolBlockProcessor implements BlockProcessor {



    @Override
    public List<ItemStack> processOutput(BlockBreakEvent info, List<ItemStack> currentOutput) {

        // Identify tool
        ItemStack toolUsed = info.getPlayer().getItemInHand();
        NBTItem nbtItem = new NBTItem(toolUsed);

        CustomToolEnum customToolEnum = null;
        try {
            customToolEnum = CustomToolEnum.valueOf(nbtItem.getString("ToolType").toUpperCase());
        } catch (IllegalArgumentException ignored) {}


        // Check whether tool is a custom donor tool
        if(customToolEnum != null) {
            // Handle tool output
            customToolEnum.getHandler(); // something like this; needs to be rewritten
        }

        return currentOutput;

    }
}
