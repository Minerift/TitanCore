package org.avaeriandev.titancore;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TitanAPI {

    protected static Map<Player, TitanPlayer> playerData = new HashMap<>();

    public static TitanPlayer getTitanPlayer(Player plr) {
        return playerData.get(plr);
    }

}
