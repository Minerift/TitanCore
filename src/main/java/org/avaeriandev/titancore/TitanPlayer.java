package org.avaeriandev.titancore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TitanPlayer {

    private OfflinePlayer plr;
    private int tickets;
    private Map<Integer, Long> activatedQuests;
    private Map<Integer, Long> completedQuests;
    
    public Map<String, Object> serialize() {
        
        Map<String, Object> serialMap = new HashMap<>();
        
        serialMap.put("uuid", plr.getUniqueID().toString());
        serialMap.put("tickets", tickets);
        serialMap.put("activated-quests", activatedQuests);
        serialMap.put("completed-quests", completedQuests);
        
        return serialMap;
    }
    
    public TitanPlayer(Map<String, Object> serialMap) {
        this.plr = Bukkit.getOfflinePlayer(UUID.fromString((String) serialMap.get("uuid")));
        this.tickets = (int) serialMap.get("tickets");
        this.activatedQuests = (Map<Integer, Long>) serialMap.get("activated-quests");
        this.completedQuests = (Map<Integer, Long>) serialMap.get("completed-quests");
    }
    
    public OfflinePlayer getPlayer() {
        return plr;
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
