package org.avaeriandev.api.util;

import org.bukkit.ChatColor;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

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

    public static float randomFloat(float min, float max) {
        float randomFloat = new Random().nextFloat();
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);
        return Float.valueOf(df.format(min + randomFloat * (max - min)));
    }

}
