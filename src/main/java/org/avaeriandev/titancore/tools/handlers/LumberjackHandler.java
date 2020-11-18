package org.avaeriandev.titancore.tools.handlers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class LumberjackHandler extends AbstractHandler {

    @Override
    public boolean breakBlock(Player plr, ItemStack tool, Block block) {

        Location location = block.getLocation().clone();

        // Get above logs
        while(true) {
            Block locBlock = location.getBlock();
            if(locBlock.getType() == Material.LOG
                    || locBlock.getType() == Material.LOG_2) {

                locBlock.setType(Material.AIR);
                block.getLocation().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.LOG, 1));
            } else {
                return true;
            }

            location.add(0, 1, 0);
        }
    }
}
