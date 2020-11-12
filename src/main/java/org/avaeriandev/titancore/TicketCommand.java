package org.avaeriandev.titancore;

import org.avaeriandev.api.util.BaseUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TicketCommand implements CommandExecutor {

    /* Format:
        /tickets add <username> <amount>
        /tickets remove <username> <amount>
        /tickets set <username> <amount>
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!sender.hasPermission("minerift.admin")) {
            sender.sendMessage(BaseUtils.chat("&cYou don't have permission!"));
            return false;
        }

        if(args.length < 3) {
            sender.sendMessage(BaseUtils.chat("&cIncorrect arguments."));
            return false;
        }

        Player plr = Bukkit.getPlayer(args[1]);

        if(plr == null) {
            sender.sendMessage(BaseUtils.chat("&cThe player specified needs to be online!"));
            return false;
        }

        TitanPlayer titanPlayer = TitanPlayerAPI.get(plr);

        // Process arguments
        switch(args[0]) {
            case "add": titanPlayer.setTickets(titanPlayer.getTickets() + Integer.valueOf(args[2])); return true;
            case "remove": titanPlayer.setTickets(titanPlayer.getTickets() - Integer.valueOf(args[2])); return true;
            case "set": titanPlayer.setTickets(Integer.valueOf(args[2])); return true;
        }

        return true;
    }
}
