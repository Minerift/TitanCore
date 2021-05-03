package org.minerift.titan.modules.warden;

import org.avaeriandev.api.util.BaseUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WardenCommand implements CommandExecutor {

    // Format: /warden <username>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("minerift.admin")) {
            sender.sendMessage(BaseUtils.chat("&cYou don't have permission!"));
            return false;
        }

        if(args.length < 1) {
            sender.sendMessage(BaseUtils.chat("&cYou need to enter a player name!"));
            return false;
        }

        Player plr = Bukkit.getPlayer(args[0]);

        if(plr == null) {
            sender.sendMessage(BaseUtils.chat("&cThe player specified needs to be online!"));
            return false;
        }

        plr.openInventory(new WardenPanel().getPanel());

        return true;
    }
}
