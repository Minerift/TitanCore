package org.minerift.titan.modules.tools;

import de.tr7zw.nbtapi.NBTItem;
import org.avaeriandev.api.util.BaseUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ToolCommand implements CommandExecutor {

    // Format: /rifttool <identifier> <username>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!sender.hasPermission("minerift.admin")) {
            sender.sendMessage(BaseUtils.chat("&cYou don't have permission!"));
            return false;
        }

        if(args.length != 2) {
            sender.sendMessage(BaseUtils.chat("&cIncorrect arguments."));
            return false;
        }

        CustomToolEnum tool = CustomToolEnum.valueOf(args[0]);
        Player plr = Bukkit.getPlayer(args[1]);

        ItemStack toolItem = tool.getTool();

        NBTItem nbtItem = new NBTItem(toolItem);
        nbtItem.setString("ToolType", tool.name());

        toolItem = nbtItem.getItem();

        plr.getInventory().addItem(toolItem);

        return true;
    }
}
