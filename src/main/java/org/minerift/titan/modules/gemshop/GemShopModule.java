package org.minerift.titan.modules.gemshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.minerift.titan.TitanPlugin;
import org.minerift.titan.TitanProfile;
import org.minerift.titan.modules.Module;
import org.minerift.titan.modules.gemshop.commands.GemShopCommand;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class GemShopModule extends Module {

    private static final int TICK = 20;
    private static final int SLOT_COUNT = 3;
    private static final int MAX_SLOT_PURCHASES = 3;
    private static final ObjectMapper MAPPER = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

    private BukkitTask timestampChecker;
    private GemShopData data;

    @Override
    protected void onEnable() {

        registerCommand("gemshop", new GemShopCommand());

        // Deserialize file (if applicable)
        try {
            File moduleFile = getModuleFile();
            this.data = moduleFile.length() != 0 ? MAPPER.readValue(moduleFile, GemShopData.class) : new GemShopData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Prepare expiration scheduler
        this.timestampChecker = new BukkitRunnable() {
            @Override
            public void run() {
                boolean hasExpired = System.currentTimeMillis() > getExpirationTimestamp();
                if(hasExpired) {

                    // Update module
                    data.setExpireTimestamp(data.nextExpireTimestamp());
                    data.setSlots(data.selectNewOffers());
                    data.setVersion(data.getVersion() + 1);

                    // Update player data
                    TitanProfile.list().forEach(profile -> profile.updateGemShopData());

                    System.out.println("Gem shop updated!");
                }
            }
        }.runTaskTimer(TitanPlugin.getInstance(), 0, 10 * TICK);
    }

    @Override
    protected void onDisable() {

        timestampChecker.cancel();
        timestampChecker = null;

        // Serialize data
        try {
            File moduleFile = getModuleFile();
            MAPPER.writeValue(moduleFile, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getVersion() {
        return data.getVersion();
    }

    public GemShopOffer[] getOffers() {
        return data.getSlots();
    }

    public long getExpirationTimestamp() {
        return data.getExpireTimestamp();
    }

    public int getSlotCount() {
        return SLOT_COUNT;
    }

    public int getMaxSlotPurchases() {
        return MAX_SLOT_PURCHASES;
    }

    private File getModuleFile() throws IOException {
        File file = new File(TitanPlugin.getInstance().getDataFolder(), "gemshop.json");
        if(!file.exists()) {
                file.createNewFile();
        }
        return file;
    }
}
