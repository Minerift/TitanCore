package org.avaeriandev.titancore;

import org.avaeriandev.titancore.commands.QuestCommand;
import org.avaeriandev.titancore.listeners.PlayerJoinListener;
import org.avaeriandev.titancore.listeners.PlayerQuitListener;
import org.avaeriandev.titancore.quest.Quest;
import org.avaeriandev.titancore.quest.QuestAPI;
import org.avaeriandev.titancore.quests.QuestType;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class TitanPlugin extends JavaPlugin {

    private static TitanPlugin instance;

    public final static File plrDataDir = new File(instance.getDataFolder(), "playerData");

    public void onEnable() {
        instance = this;

        registerQuest(QuestType.E_WOOD);

        registerListener(new PlayerJoinListener());
        registerListener(new PlayerQuitListener());

        registerCommand("quest", new QuestCommand());

    }

    public void onDisable() {

    }

    private void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
    private void registerCommand(String prefix, CommandExecutor executor) {
        instance.getCommand(prefix).setExecutor(executor);
    }
    private void registerQuest(QuestType quest) {
        QuestAPI.registerQuest(quest.getId(), quest.getQuest());
    }

    public static TitanPlugin getInstance() {
        return instance;
    }

}
