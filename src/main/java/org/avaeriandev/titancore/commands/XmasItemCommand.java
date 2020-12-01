package org.avaeriandev.titancore.commands;

import de.tr7zw.nbtapi.NBTItem;
import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.api.util.ItemBuilder;
import org.avaeriandev.titancore.christmas.XmasItemEnum;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class XmasItemCommand implements CommandExecutor {


    // Format: /xmas <item> <username> <amount>

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

        XmasItemEnum xmasItemEnum = XmasItemEnum.valueOf(args[0].toUpperCase());
        int amount = Integer.valueOf(args[2]);

        ItemStack item = new ItemBuilder(new ItemStack(xmasItemEnum.getItem())).setAmount(amount).create();
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("XmasItem", xmasItemEnum.name());
        item = nbtItem.getItem();

        if(xmasItemEnum == XmasItemEnum.PRESENT) {
            plr.sendMessage(BaseUtils.chat("&aYou found a present!"));
            plr.playSound(plr.getLocation(), Sound.LEVEL_UP, 1, 2);
            FireworksCommand.playFireworkEffect(plr);
        }

        plr.getInventory().addItem(item);
        plr.updateInventory();

        return true;
    }
}
