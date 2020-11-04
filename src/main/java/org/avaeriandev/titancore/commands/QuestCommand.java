package org.avaeriandev.titancore.commands;

import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.titancore.TitanPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QuestCommand implements CommandExecutor {

    // Format: /quest <identifier> <username>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            sender.sendMessage(BaseUtils.chat("&cYou have no permission!"));
            return false;
        }



        // TODO: process quest state for player
        return false;

    }


}
