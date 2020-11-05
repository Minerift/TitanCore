package org.avaeriandev.titancore;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TitanPlayerAPI {

    private static Map<OfflinePlayer, TitanPlayer> titanPlayerDictionary = new HashMap<>();
    private static Yaml yaml = new Yaml();

    public static TitanPlayer get(OfflinePlayer plr) {
        return playerData.get(plr);
    }
    
    public static TitanPlayer load(OfflinePlayer plr) {
        
        // Prevent player from being loaded multiple times
        if(titanPlayerDictionary.contains(plr)) {
            System.out.println(plr.getName() + " has already been loaded!");
            return;
        }
        
        // Obtain file from file name format
        File saveFile = getDataFileFromFormat(plr);
        if(saveFile == null) {
            System.err.println("Player data for " + plr.getName() + " was unable to be retrieved from file!");
            return;
        }
        
        // Read data from file
        Map<String, Object> serialMap = (Map<String, Object>) yaml.load(new FileReader(saveFile));
        
        // Attempt to load to TitanPlayer object
        TitanPlayer titanPlayer = TitanPlayer.deserialize(serialMap);
        if(titanPlayer == null) {
            System.err.println(plr.getName() + " failed to load!");
            return;
        }
        
        // Store in dictionary to be accessed later
        titanPlayerDictionary.put(plr, titanPlayer);
        
    }
    
    public static void unload(OfflinePlayer plr) {
        playerData.remove(plr);
    }
    
    public static void save(OfflinePlayer plr) {
        
        TitanPlayer titanPlayer = get(plr);
        File saveFile = getDataFileFromFormat(plr);
        if(saveFile == null) {
            System.err.println("Player data for " + plr.getName() + " was unable to be dumped to file!");
            return;
        }
            
        Map<String, Object> serialMap = titanPlayer.serialize();
        
        yaml.dump(serialMap, new FileWriter(saveFile));
        
        
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

}
