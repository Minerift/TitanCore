package org.avaeriandev.titancore;

import org.bukkit.entity.Player;

import java.util.Map;

public class TitanAPI {

    protected static Map<Player, TitanPlayer> playerData;

    public static TitanPlayer getTitanPlayer(Player plr) {
        return playerData.get(plr);
    }

}
