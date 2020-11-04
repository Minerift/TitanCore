package org.avaeriandev.api.util;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BaseUtils {

    public static String chat(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> lore(List<String> lore) {
        for(int i = 0; i < lore.size(); i++) {
            lore.set(i, chat(lore.get(i)));
        }
        return lore;
    }



}
