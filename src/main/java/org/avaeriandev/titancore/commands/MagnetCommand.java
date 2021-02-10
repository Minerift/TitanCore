package org.avaeriandev.titancore.commands;

import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.TitanPlayerAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MagnetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Require player to execute this command
        if(!(sender instanceof Player)) {
            return false;
        }

        Map<Boolean, String> messages = new HashMap<>();
        messages.put(true, "&aMagnet activated!"); // set to true
        messages.put(false, "&cMagnet deactivated!"); // set to false

        Player plr = (Player) sender;
        TitanPlayer titanPlayer = TitanPlayerAPI.get(plr);

        if(!titanPlayer.hasMagnetAbility()) {
            plr.sendMessage(BaseUtils.chat("&cPurchase access to this ability at &nstore.minerift.org!"));
        } else {
            boolean newMagnetStatus = !titanPlayer.isMagnetActive();
            titanPlayer.setMagnetActive(newMagnetStatus);
            plr.sendMessage(BaseUtils.chat(messages.get(newMagnetStatus)));
        }

        return true;
    }
}
