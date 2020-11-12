package org.avaeriandev.titancore;

import org.avaeriandev.titancore.quest.QuestData;
import org.avaeriandev.titancore.quests.QuestEnum;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;

public class TitanPlayer {

    private OfflinePlayer plr;
    private int tickets;
    private Map<QuestEnum, QuestData> questDataMap;
    
    public Map<String, Object> serialize() {

        // Generate object serialMap
        Map<String, Object> serialMap = new HashMap<>();

        // Generate quest serialMap
        Map<String, Object> questSerialMap = new HashMap<>();
        for(Map.Entry<QuestEnum, QuestData> questEntry : questDataMap.entrySet()) {
            questSerialMap.put(questEntry.getKey().name(), questEntry.getValue().serialize());
        }

        serialMap.put("tickets", tickets);
        serialMap.put("quest-data", questSerialMap);
        
        return serialMap;
    }
    
    public static TitanPlayer deserialize(OfflinePlayer plr, Map<String, Object> serialMap) {
        return new TitanPlayer(plr, serialMap != null ? serialMap : new HashMap<>());
    }
    
    private TitanPlayer(OfflinePlayer plr, Map<String, Object> serialMap) {
        this.plr = plr;
        this.tickets = (int) attemptToGet(serialMap, "tickets", 0);
        this.questDataMap = new HashMap<>();

        // Deserialize quest data
        Map<String, Map<String, Object>> questDataSerialMap = (Map<String, Map<String, Object>>) attemptToGet(serialMap, "quest-data", new HashMap<>());
        for(Map.Entry<String, Map<String, Object>> questEntry : questDataSerialMap.entrySet()) {
            String questName = questEntry.getKey();
            Map<String, Object> questSerialMap = questEntry.getValue();
            questDataMap.put(QuestEnum.valueOf(questName.toUpperCase()), new QuestData(questSerialMap));
        }
    }
    
    public OfflinePlayer getPlayer() {
        return plr;
    }

    public int getTickets() {
        return tickets;
    }

    public Map<QuestEnum, QuestData> getQuestDataMap() {
        return questDataMap;
    }

    public QuestData getDataForQuest(QuestEnum questEnum) {
        return questDataMap.containsKey(questEnum) ? questDataMap.get(questEnum) : new QuestData(questEnum);
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    private Object attemptToGet(Map<String, Object> serialMap, String key, Object fallback) {
        return serialMap.containsKey(key) ? serialMap.get(key) : fallback;
    }
}
