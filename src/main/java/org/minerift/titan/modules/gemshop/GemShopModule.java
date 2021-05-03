package org.minerift.titan.modules.gemshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.bukkit.scheduler.BukkitTask;
import org.minerift.titan.modules.Module;

import java.io.File;
import java.io.IOException;

public class GemShopModule extends Module {

    private static final int SLOT_COUNT = 3;
    private static final File MODULE_FILE = null;
    private static final ObjectMapper MAPPER = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

    private BukkitTask timestampChecker;
    private GemShopData data;

    @Override
    protected void onEnable() {

        // Deserialize file (if applicable)
        try {
            this.data = MODULE_FILE.length() != 0 ? MAPPER.readValue(MODULE_FILE, GemShopData.class) : new GemShopData();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onDisable() {

        timestampChecker.cancel();
        timestampChecker = null;

        // Serialize data
        try {
            MAPPER.writeValue(MODULE_FILE, data);
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
}
