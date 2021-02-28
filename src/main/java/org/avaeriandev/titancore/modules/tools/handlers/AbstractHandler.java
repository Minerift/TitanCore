package org.avaeriandev.titancore.modules.tools.handlers;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractHandler {

    public abstract boolean breakBlock(Player plr, ItemStack tool, Block block);

}
