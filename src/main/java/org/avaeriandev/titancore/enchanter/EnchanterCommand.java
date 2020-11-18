package org.avaeriandev.titancore.enchanter;

import org.avaeriandev.api.util.BaseUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnchanterCommand implements CommandExecutor {

    // Format: /enchanter <username>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!sender.hasPermission("minerift.admin")) {
            sender.sendMessage(BaseUtils.chat("&cYou don't have permission!"));
            return false;
        }

        Player plr = Bukkit.getPlayer(args[0]);
        plr.openInventory(new EnchanterPanel().getPanel());

        return false;
    }
}
