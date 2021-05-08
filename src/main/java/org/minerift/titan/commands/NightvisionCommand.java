package org.minerift.titan.commands;

import org.avaeriandev.api.util.BaseUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightvisionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return false;
        }

        Player plr = (Player) sender;

        if(!plr.hasPermission("donor.nightvision")) {
            plr.sendMessage(BaseUtils.chat("&cSorry, you don't have permission."));
            return true;
        }

        if(!plr.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
            plr.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 0));
            plr.sendMessage(BaseUtils.chat("&aNightvision enabled!"));
        } else {
            plr.removePotionEffect(PotionEffectType.NIGHT_VISION);
            plr.sendMessage(BaseUtils.chat("&cNightvision disabled!"));
        }

        return true;

    }
}
