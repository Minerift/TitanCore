package org.avaeriandev.titancore;

import org.avaeriandev.titancore.modules.quests.util.QuestData;
import org.avaeriandev.titancore.modules.quests.QuestEnum;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class TitanPlayerAPI {

    private static Map<OfflinePlayer, TitanPlayer> titanPlayerDictionary = new HashMap<>();
    private static Yaml yaml = new Yaml();

    public static TitanPlayer get(OfflinePlayer plr) {
        return titanPlayerDictionary.get(plr);
    }
    
    public static TitanPlayer load(OfflinePlayer plr) {
        
        // Prevent player from being loaded multiple times
        if(titanPlayerDictionary.containsKey(plr)) {
            System.out.println(plr.getName() + " has already been loaded!");
            return null;
        }
        
        // Obtain file from file name format
        File saveFile = getDataFileFromFormat(plr);
        if(saveFile == null) {
            System.err.println("Player data for " + plr.getName() + " was unable to be retrieved from file!");
            return null;
        }
        
        // Read data from file
        Map<String, Object> serialMap;
        try {
            serialMap = (Map<String, Object>) yaml.load(new FileReader(saveFile));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }

        // Attempt to load to TitanPlayer object
        TitanPlayer titanPlayer = TitanPlayer.deserialize(plr, null); // TODO null for compatibility
        if(titanPlayer == null) {
            System.err.println(plr.getName() + " failed to load!");
            return null;
        }
        
        // Store in dictionary to be accessed later
        titanPlayerDictionary.put(plr, titanPlayer);
        return titanPlayer;
    }
    
    public static void unload(OfflinePlayer plr) {
        titanPlayerDictionary.remove(plr);
    }
    
    public static void save(OfflinePlayer plr) {
        
        TitanPlayer titanPlayer = titanPlayerDictionary.get(plr);
        File saveFile = getDataFileFromFormat(plr);
        if(saveFile == null) {
            System.err.println("Player data for " + plr.getName() + " was unable to be dumped to file!");
            return;
        }

        //Map<String, Object> serialMap = titanPlayer.serialize();
        Map<String, Object> serialMap = new HashMap<>(); // TODO compatibility

        try {
            yaml.dump(serialMap, new FileWriter(saveFile));
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
    }
    
    private static File getDataFileFromFormat(OfflinePlayer plr) {
        
        File saveFile = null;
        try {
            
            saveFile = new File(TitanPlugin.plrDataDir, plr.getUniqueId().toString() + ".yml");
            if(!saveFile.exists()) saveFile.createNewFile();
            
        } catch (IOException e) {
            
            System.err.println("System failed to create player data file!");
            e.printStackTrace();
        }
        return saveFile;
    }

    public static void checkQuestStateExpiration() {

        // Iterate every player on server
        for(TitanPlayer titanPlayer : titanPlayerDictionary.values()) {

            Player plr = titanPlayer.getPlayer().getPlayer();

            // Iterate through all quests active and/or completed
            Map<QuestEnum, QuestData> questDataMap = titanPlayer.getQuestDataMap();
            for(Map.Entry<QuestEnum, QuestData> questDataEntry : new ArrayList<>(questDataMap.entrySet())) {
                QuestEnum questEnum = questDataEntry.getKey();
                QuestData questData = questDataEntry.getValue();

                // Verify if quest has expired
                if(System.currentTimeMillis() >= questData.getStateExpireTimestamp()) {
                    // Set state as inactive
                    titanPlayer.getQuestDataMap().remove(questEnum);
                }
            }
        }
    }
}
