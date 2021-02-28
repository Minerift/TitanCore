package org.avaeriandev.titancore;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.apache.commons.lang.Validate;
import org.avaeriandev.titancore.modules.gemshop.GemShopManager;
import org.avaeriandev.titancore.modules.quests.QuestEnum;
import org.avaeriandev.titancore.modules.quests.util.QuestData;
import org.bukkit.OfflinePlayer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TitanPlayer {

    private static final ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
    private static BiMap<OfflinePlayer, TitanPlayer> plrDictionary = HashBiMap.create();

    public static TitanPlayer get(OfflinePlayer plr) {
        return plrDictionary.get(plr);
    }

    public static TitanPlayer load(OfflinePlayer plr) {

        // Prevent player from being loaded multiple times
        if(plrDictionary.containsKey(plr)) {
            System.out.println(plr.getName() + " has already been loaded!");
            return null;
        }

        // Obtain file from file name format
        File file = getDataFileFromFormat(plr);
        Validate.notNull(file, "Player data for " + plr.getName() + " was unable to be retrieved from file!");

        // Deserialize data
        TitanPlayer titanPlayer = deserialize(plr, file);
        Validate.notNull(titanPlayer, "Player data for " + plr.getName() + " could not be loaded from file properly!");

        plrDictionary.put(plr, titanPlayer);
        return titanPlayer;
    }

    public static void unload(OfflinePlayer plr) {
        if(plrDictionary.containsKey(plr)) {

            TitanPlayer titanPlayer = plrDictionary.get(plr);
            File file = getDataFileFromFormat(plr);
            try {
                mapper.writeValue(file, titanPlayer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            plrDictionary.remove(plr);
        }
    }

    private static File getDataFileFromFormat(OfflinePlayer plr) {

        File saveFile = null;
        try {

            saveFile = new File(TitanPlugin.plrDataDir, plr.getUniqueId().toString() + ".json");
            if(!saveFile.exists()) saveFile.createNewFile();

        } catch (IOException e) {

            System.err.println("System failed to create player data file!");
            e.printStackTrace();
        }
        return saveFile;
    }

    private static TitanPlayer deserialize(OfflinePlayer plr, File file) {
        Validate.notNull(plr, "Player cannot be null!");
        Validate.notNull(file, "File cannot be null!");

        TitanPlayer titanPlayer = null;
        try {

            titanPlayer = file.length() != 0 ? mapper.readValue(file, TitanPlayer.class) : new TitanPlayer();
            titanPlayer.postConstruction(plr);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return titanPlayer;
    }

    @JsonIgnore
    private OfflinePlayer plr = null;

    private boolean isMagnetActive = false;
    private int tickets = 0;
    private Map<QuestEnum, QuestData> questDataMap = new HashMap<>();

    // Gem Shop
    private int[] slotPurchases = new int[GemShopManager.slotCount];
    private int gemShopVersion = GemShopManager.version;

    private TitanPlayer() {

    }

    private void postConstruction(OfflinePlayer plr) {
        this.plr = plr;
    }

    @JsonIgnore
    public OfflinePlayer getPlayer() {
        return plr;
    }

    @JsonIgnore
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
            Arrays.fill(slotPurchases, 0);
        }
    }
}
