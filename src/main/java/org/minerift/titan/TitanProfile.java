package org.minerift.titan;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.apache.commons.lang.Validate;
import org.minerift.titan.modules.gemshop.GemShopModule;
import org.minerift.titan.modules.quest.QuestEnum;
import org.minerift.titan.modules.quest.system.QuestData;
import org.bukkit.OfflinePlayer;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 *  Represents a Titan game client profile
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TitanProfile {

    // Handle TitanProfile loading
    private static final ObjectMapper MAPPER = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
    private static BiMap<OfflinePlayer, TitanProfile> plrDictionary = HashBiMap.create();

    public static TitanProfile get(OfflinePlayer plr) {
        return plrDictionary.get(plr);
    }
    public static TitanProfile load(OfflinePlayer plr) {

        // Prevent player from being loaded multiple times
        if(plrDictionary.containsKey(plr)) {
            System.out.println(plr.getName() + " has already been loaded!");
            return null;
        }

        // Obtain file from file name format
        File file = getDataFileFromFormat(plr);
        Validate.notNull(file, "Player data for " + plr.getName() + " was unable to be retrieved from file!");

        // Deserialize data
        TitanProfile titanProfile = deserialize(plr, file);
        Validate.notNull(titanProfile, "Player data for " + plr.getName() + " could not be loaded from file properly!");

        plrDictionary.put(plr, titanProfile);
        return titanProfile;
    }
    public static Set<TitanProfile> list() {
        return plrDictionary.values();
    }
    public static void unload(OfflinePlayer plr) {
        if(plrDictionary.containsKey(plr)) {

            TitanProfile titanProfile = plrDictionary.get(plr);
            File file = getDataFileFromFormat(plr);
            try {
                MAPPER.writeValue(file, titanProfile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            plrDictionary.remove(plr);
        }
    }
    private static TitanProfile deserialize(OfflinePlayer plr, File file) {
        Validate.notNull(plr, "Player cannot be null!");
        Validate.notNull(file, "File cannot be null!");

        TitanProfile titanProfile = null;
        try {

            titanProfile = file.length() != 0 ? MAPPER.readValue(file, TitanProfile.class) : new TitanProfile();
            titanProfile.postConstruction(plr);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return titanProfile;
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

    // Module references
    @JsonIgnore
    private final GemShopModule GEM_SHOP_MODULE = TitanPlugin.getModule(GemShopModule.class);

    @JsonIgnore
    private OfflinePlayer plr = null;

    private boolean isMagnetActive = false;
    private int tickets = 0;
    private Map<QuestEnum, QuestData> questDataMap = new HashMap<>();

    // Gem Shop
    private int[] slotPurchases = new int[GEM_SHOP_MODULE.getSlotCount()];
    private int gemShopVersion = GEM_SHOP_MODULE.getVersion();

    // Used for deserialization
    private TitanProfile() {}
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

    public void setSlotPurchases(int[] slotPurchases) {
        this.slotPurchases = slotPurchases;
    }

    @JsonIgnore
    public int getLocalGemShopVersion() {
        return gemShopVersion;
    }

    @JsonIgnore
    public void updateGemShopData() {
        // Update gem shop version
        gemShopVersion = GEM_SHOP_MODULE.getVersion();

        // Reset slot purchases
        slotPurchases = new int[GEM_SHOP_MODULE.getSlotCount()];
        Arrays.fill(slotPurchases, 0);
    }

    @JsonIgnore
    public boolean canPurchase(int slot) {
        return slotPurchases[slot] < GEM_SHOP_MODULE.getMaxSlotPurchases();
    }
}
