package org.avaeriandev.titancore.commands;

import de.tr7zw.nbtapi.NBTItem;
import org.avaeriandev.api.util.ItemBuilder;
import org.avaeriandev.titancore.modules.explosives.ExplosiveType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DynamiteCommand implements CommandExecutor {

    // Command: /dynamite <type> <username> <amount>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        ExplosiveType explosiveType = ExplosiveType.valueOf(args[0].toUpperCase());
        Player plr = Bukkit.getPlayer(args[1]);
        int amount = Integer.valueOf(args[2]);

        ItemStack item = new ItemBuilder(explosiveType.getItem().clone()).setAmount(amount).create();
        NBTItem nbtItem = new NBTItem(item);

        nbtItem.setString("explosive", explosiveType.name());

        plr.getInventory().addItem(nbtItem.getItem());

        return true;
    }
}
