package org.avaeriandev.titancore;

import org.avaeriandev.titancore.gemshop.GemShopManager;
import org.avaeriandev.titancore.quests.QuestEnum;
import org.avaeriandev.titancore.quests.util.QuestData;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;

public class TitanPlayer {

    private OfflinePlayer plr;
    private boolean isMagnetActive;
    private int tickets;
    private Map<QuestEnum, QuestData> questDataMap;

    // Gem Shop
    private int[] slotPurchases;
    private int gemShopVersion;

    public Map<String, Object> serialize() {

        // Generate object serialMap
        Map<String, Object> serialMap = new HashMap<>();

        // Generate quest serialMap
        Map<String, Object> questSerialMap = new HashMap<>();
        for(Map.Entry<QuestEnum, QuestData> questEntry : questDataMap.entrySet()) {
            questSerialMap.put(questEntry.getKey().name(), questEntry.getValue().serialize());
        }

        serialMap.put("is-magnet-active", isMagnetActive);
        serialMap.put("tickets", tickets);
        serialMap.put("quest-data", questSerialMap);

        // Gem Shop
        serialMap.put("slot-purchases", slotPurchases);
        serialMap.put("gem-shop-version", gemShopVersion);
        
        return serialMap;
    }
    
    public static TitanPlayer deserialize(OfflinePlayer plr, Map<String, Object> serialMap) {
        return new TitanPlayer(plr, serialMap != null ? serialMap : new HashMap<>());
    }
    
    private TitanPlayer(OfflinePlayer plr, Map<String, Object> serialMap) {
        this.plr = plr;
        this.isMagnetActive = (boolean) attemptToGet(serialMap, "is-magnet-active", false);
        this.tickets = (int) attemptToGet(serialMap, "tickets", 0);
        this.questDataMap = new HashMap<>();

        // Gem Shop
        this.slotPurchases = (int[]) attemptToGet(serialMap, "slot-purchases", new int[3]);
        this.gemShopVersion = (int) attemptToGet(serialMap, "gem-shop-version", -1);

        // Deserialize quest data
        Map<String, Map<String, Object>> questDataSerialMap = (Map<String, Map<String, Object>>) attemptToGet(serialMap, "quest-data", new HashMap<>());
        for(Map.Entry<String, Map<String, Object>> questEntry : questDataSerialMap.entrySet()) {
            String questName = questEntry.getKey();
            Map<String, Object> questSerialMap = questEntry.getValue();

            // Attempt to load quest enum
            try {
                questDataMap.put(QuestEnum.valueOf(questName.toUpperCase()), new QuestData(questSerialMap));
            } catch(IllegalArgumentException e) {
                // Quest enum stored was found to be invalid
                // DON'T LOAD THAT SPECIFIC QUEST
            }
        }
    }
    
    public OfflinePlayer getPlayer() {
        return plr;
    }

    public boolean isMagnetActive() {
        return isMagnetActive;
    }

    public boolean hasMagnetAbility() {
        if(!plr.isOnline()) { return false; }
        return plr.getPlayer().hasPermission("minerift.abilities.magnet");
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

    public void setMagnetActive(boolean isActive) {
        this.isMagnetActive = isActive;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public int[] getSlotPurchases() {
        return slotPurchases;
    }

    public void updateGemShopData() {
        if(GemShopManager.version > gemShopVersion) {
            // Catch up
            gemShopVersion = GemShopManager.version;

            // Refresh with new empty array
            slotPurchases = new int[GemShopManager.slotCount];
            for(int i = 0; i < slotPurchases.length; i++) {
                slotPurchases[i] = 0;
            }
        }
    }

    private Object attemptToGet(Map<String, Object> serialMap, String key, Object fallback) {
        return serialMap.containsKey(key) ? serialMap.get(key) : fallback;
    }
}
