package org.avaeriandev.titancore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TitanPlayer {

    private Player plr;
    private int tickets;
    private Map<Integer, Long> activatedQuests;
    private Map<Integer, Long> completedQuests;

    public static TitanPlayer load(Player plr) {

        // Check if player is already loaded
        if(TitanAPI.playerData.containsKey(plr)) {
            return TitanAPI.playerData.get(plr);
        }

        // Check if player has save file
        File saveFile = new File(TitanPlugin.plrDataDir, plr.getUniqueId().toString() + ".yml");
        if(saveFile.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(saveFile);

            int tickets = config.getInt("tickets");
            Map<Integer, Long> activatedQuests = (Map<Integer, Long>) config.get("activated-quests");
            Map<Integer, Long> completedQuests = (Map<Integer, Long>) config.get("completed-quests");

            TitanPlayer titanPlayer = new TitanPlayer(plr, tickets, activatedQuests, completedQuests);
            TitanAPI.playerData.put(plr, titanPlayer);
            return titanPlayer;
        }

        // Load empty data for player
        TitanPlayer titanPlayer = new TitanPlayer(plr,0, new HashMap<Integer, Long>(), new HashMap<Integer, Long>());
        TitanAPI.playerData.put(plr, titanPlayer);
        return titanPlayer;
    }

    private TitanPlayer(Player plr, int tickets, Map<Integer, Long> activatedQuests, Map<Integer, Long> completedQuests) {
        this.tickets = tickets;
        this.activatedQuests = activatedQuests;
        this.completedQuests = completedQuests;
    }

    public void save() {
        try {
            File saveFile = new File(TitanPlugin.plrDataDir, plr.getUniqueId().toString() + ".yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(saveFile);

            config.set("tickets", tickets);
            config.set("activated-quests", activatedQuests);
            config.set("completed-quests", completedQuests);

            config.save(saveFile);
        } catch(IOException e) {
            System.err.println("There was a fatal error saving player data!");
            e.printStackTrace();
        }
    }

    public void unregister() {
        TitanAPI.playerData.remove(plr);
    }

    public int getTickets() {
        return tickets;
    }

    public Map<Integer, Long> getActivatedQuests() {
        return activatedQuests;
    }

    public Map<Integer, Long> getCompletedQuests() {
        return completedQuests;
    }
}
