package org.minerift.titan;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.minerift.titan.api.external.Placeholders;
import org.minerift.titan.commands.*;
import org.minerift.titan.listeners.*;
import org.minerift.titan.modules.Module;
import org.minerift.titan.modules.tools.ToolCommand;
import org.vexar.titan.commands.*;
import org.vexar.titan.listeners.*;
import org.minerift.titan.modules.auction.AuctionModule;
import org.minerift.titan.modules.commissary.CommissaryModule;
import org.minerift.titan.modules.enchanter.EnchanterCommand;
import org.minerift.titan.modules.explosives.ExplosiveListener;
import org.minerift.titan.modules.quest.QuestModule;
import org.minerift.titan.modules.warden.WardenCommand;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TitanPlugin extends JavaPlugin {

    private static TitanPlugin instance;

    public static File plrDataDir;

    private static Map<Class<? extends Module>, Module> modules;

    public void onEnable() {
        instance = this;
        modules = new HashMap<>();
        plrDataDir = new File(instance.getDataFolder(), "playerData");
        if(!plrDataDir.exists()) plrDataDir.mkdir();

        // Register modules
        registerModule(new AuctionModule());
        registerModule(new CommissaryModule());
        registerModule(new QuestModule());

        // Register other listeners
        registerListener(new PlayerJoinListener());
        registerListener(new PlayerQuitListener());
        registerListener(new BlockBreakListener());
        registerListener(new PVPListener());
        registerListener(new PlayerDeathListener());
        registerListener(new ExplosiveListener());

        // Register commands
        registerCommand("warden", new WardenCommand());
        registerCommand("enchanter", new EnchanterCommand());
        registerCommand("ticket", new TicketCommand());
        registerCommand("fw", new FireworksCommand());
        registerCommand("rifttool", new ToolCommand());
        registerCommand("dynamite", new DynamiteCommand());

        registerCommand("speed", new SpeedCommand());
        registerCommand("haste", new HasteCommand());
        registerCommand("magnet", new MagnetCommand());

        // Register placeholders
        new Placeholders().register();

    }

    public void onDisable() {

        // Save player data
        Bukkit.getOnlinePlayers().forEach(plr -> TitanProfile.unload(plr));

        // Disable all modules
        modules.values().forEach(module -> module.disable());
        modules.clear();
    }

    @Deprecated
    private void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    @Deprecated
    private void registerCommand(String prefix, CommandExecutor executor) {
        instance.getCommand(prefix).setExecutor(executor);
    }

    private void registerModule(Module module) {
        if(!modules.containsKey(module.getClass())) {
            modules.put(module.getClass(), module);
            module.enable();
        }
    }

    public static <E extends Module> E getModule(Class<? extends Module> clazz) {
        return (E) modules.get(clazz);
    }

    public static TitanPlugin getInstance() {
        return instance;
    }

}
