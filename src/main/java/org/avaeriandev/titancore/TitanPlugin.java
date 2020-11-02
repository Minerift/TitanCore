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
        registerQuest(QuestType.E_LEATHER);
        registerQuest(QuestType.E_MINE);
        registerQuest(QuestType.E_VOODOO);

        registerQuest(QuestType.D_CONDUCTOR);
        registerQuest(QuestType.D_MIA_STONER);
        registerQuest(QuestType.D_STANKY_JAMES);

        registerQuest(QuestType.C_COOK_CHEF);
        registerQuest(QuestType.C_COOK_BOY);
        registerQuest(QuestType.C_COOK_ARE);
        registerQuest(QuestType.C_COOK_DEE);
        registerQuest(QuestType.C_WILE_E);
        registerQuest(QuestType.C_KURT_THROAT);
        registerQuest(QuestType.C_JOHNY_APPLEPOOP);

        registerQuest(QuestType.B_DESIGNER_DICK);
        registerQuest(QuestType.B_DIRTY_DAN);
        registerQuest(QuestType.B_DJ_MINOR);
        registerQuest(QuestType.B_FRANCIS);
        registerQuest(QuestType.B_BENRY);
        registerQuest(QuestType.B_GUSTOV);

        registerQuest(QuestType.A_DOOD);
        registerQuest(QuestType.A_MINER);

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
