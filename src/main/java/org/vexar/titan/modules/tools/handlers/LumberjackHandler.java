package org.vexar.titan.modules.tools.handlers;

import me.clip.ezblocks.EZBlocks;
import org.vexar.titan.modules.tools.MagnetHandler;
import org.vexar.titan.VexarProfile;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class LumberjackHandler extends AbstractHandler {

    @Override
    public boolean breakBlock(Player plr, ItemStack tool, Block block) {

        Location location = block.getLocation().clone();
        VexarProfile vexarProfile = VexarProfile.get(plr);

        MagnetHandler magnetHandler = new MagnetHandler(vexarProfile) {
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

        // Get above logs
        while(true) {
            Block locBlock = location.getBlock();
            if(locBlock.getType() == Material.LOG || locBlock.getType() == Material.LOG_2) {

                // Handle magnet
                ItemStack logItem = new ItemStack(Material.LOG, 1);
                magnetHandler.handleMagnet(locBlock, Arrays.asList(logItem), true);
            } else {
                return true;
            }

            location.add(0, 1, 0);
        }
    }
}
