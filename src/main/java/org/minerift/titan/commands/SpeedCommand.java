package org.minerift.titan.commands;

import org.avaeriandev.api.util.BaseUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return false;
        }

        Player plr = (Player) sender;

        if(!plr.hasPermission("donor.speed")) {
            plr.sendMessage(BaseUtils.chat("&cSorry, you don't have permission."));
            return true;
        }

        if(!plr.hasPotionEffect(PotionEffectType.SPEED)) {
            plr.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 0));
            plr.sendMessage(BaseUtils.chat("&aSpeed enabled!"));
        } else {
            plr.removePotionEffect(PotionEffectType.SPEED);
            plr.sendMessage(BaseUtils.chat("&cSpeed disabled!"));
        }

        return true;
    }
}
