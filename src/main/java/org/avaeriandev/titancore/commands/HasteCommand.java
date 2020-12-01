package org.avaeriandev.titancore.commands;

import org.avaeriandev.api.util.BaseUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HasteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return false;
        }

        Player plr = (Player) sender;

        if(!plr.hasPermission("donor.haste")) {
            plr.sendMessage(BaseUtils.chat("&cSorry, you don't have permission."));
            return true;
        }

        if(!plr.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
            plr.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 999999, 0));
            plr.sendMessage(BaseUtils.chat("&aHaste enabled!"));
        } else {
            plr.removePotionEffect(PotionEffectType.FAST_DIGGING);
            plr.sendMessage(BaseUtils.chat("&cHaste disabled!"));
        }

        return true;

    }
}
