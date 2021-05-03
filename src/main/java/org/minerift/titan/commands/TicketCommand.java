package org.minerift.titan.commands;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import org.avaeriandev.api.util.BaseUtils;
import org.minerift.titan.TitanProfile;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TicketCommand implements CommandExecutor {

    /* Format:
        /tickets add <username> <amount>
        /tickets remove <username> <amount>
        /tickets set <username> <amount>
        /tickets ezblocks <username> <amount>
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

        TitanProfile titanProfile = TitanProfile.get(plr);

        // Process arguments
        switch(args[0]) {
            case "add": titanProfile.setTickets(titanProfile.getTickets() + Integer.valueOf(args[2])); return true;
            case "remove": titanProfile.setTickets(titanProfile.getTickets() - Integer.valueOf(args[2])); return true;
            case "set": titanProfile.setTickets(Integer.valueOf(args[2])); return true;
            case "ezblocks":
                titanProfile.setTickets(titanProfile.getTickets() + Integer.valueOf(args[2]));
                ActionBarAPI.sendActionBar(plr, BaseUtils.chat("&aYou obtained " + args[2] + " gems!"));
                plr.playSound(plr.getLocation(), Sound.ORB_PICKUP, 1F, BaseUtils.randomFloat(1.5F, 2.1F));
                return true;
            default:
                sender.sendMessage(BaseUtils.chat("&cUnknown subcommand."));
                return false;
        }
    }
}
