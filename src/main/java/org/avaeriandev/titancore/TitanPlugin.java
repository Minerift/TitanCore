package org.avaeriandev.titancore;

import org.avaeriandev.titancore.listeners.PlayerJoinListener;
import org.avaeriandev.titancore.listeners.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class TitanPlugin extends JavaPlugin {

    private static TitanPlugin instance;

    public final static File plrDataDir = new File(instance.getDataFolder(), "playerData");

    public void onEnable() {
        instance = this;

        registerListener(new PlayerJoinListener());
        registerListener(new PlayerQuitListener());
    }

    public void onDisable() {

    }

    private void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public static TitanPlugin getInstance() {
        return instance;
    }

}
