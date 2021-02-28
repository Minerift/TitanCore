package org.avaeriandev.titancore;

import org.avaeriandev.titancore.api.external.Placeholders;
import org.avaeriandev.titancore.modules.Module;
import org.avaeriandev.titancore.modules.auction.AuctionModule;
import org.avaeriandev.titancore.commands.*;
import org.avaeriandev.titancore.modules.commissary.CommissaryCommand;
import org.avaeriandev.titancore.modules.commissary.CommissarySignListener;
import org.avaeriandev.titancore.modules.enchanter.EnchanterCommand;
import org.avaeriandev.titancore.modules.explosives.ExplosiveListener;
import org.avaeriandev.titancore.listeners.*;
import org.avaeriandev.titancore.modules.quests.QuestCommand;
import org.avaeriandev.titancore.modules.tools.ToolCommand;
import org.avaeriandev.titancore.modules.warden.WardenCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TitanPlugin extends JavaPlugin {

    private static TitanPlugin instance;
    private static BukkitTask questStateCheckTimer;

    public static File plrDataDir;

    private static Map<Class<? extends Module>, Module> modules;

    public void onEnable() {
        instance = this;
        modules = new HashMap<>();
        plrDataDir = new File(instance.getDataFolder(), "playerData");
        if(!plrDataDir.exists()) plrDataDir.mkdir();

        questStateCheckTimer = new BukkitRunnable() {
            @Override
            public void run() {
                System.out.println("Updating quest states if expired...");
                TitanPlayerAPI.checkQuestStateExpiration();
                System.out.println("Quest states updated.");
            }
        }.runTaskTimer(this, 0, 20 * TimeUnit.SECONDS.toSeconds(30));

        // Register modules
        registerModule(new AuctionModule());

        // Register other listeners
        registerListener(new PlayerJoinListener());
        registerListener(new PlayerQuitListener());
        registerListener(new BlockBreakListener());
        registerListener(new PVPListener());
        registerListener(new PlayerDeathListener());
        registerListener(new ExplosiveListener());
        registerListener(new CommissarySignListener());

        // Register commands
        registerCommand("commissary", new CommissaryCommand());
        registerCommand("warden", new WardenCommand());
        registerCommand("enchanter", new EnchanterCommand());
        registerCommand("ticket", new TicketCommand());
        registerCommand("quest", new QuestCommand());
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

        questStateCheckTimer.cancel();

        // Save player data
        Bukkit.getOnlinePlayers().forEach(plr -> TitanPlayer.unload(plr));

        modules.values().forEach(module -> module.terminate());
        modules.clear();
    }

    private void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
    private void registerCommand(String prefix, CommandExecutor executor) {
        instance.getCommand(prefix).setExecutor(executor);
    }

    private void registerModule(Module module) {
        if(!modules.containsKey(module.getClass())) {
            modules.put(module.getClass(), module);
            module.start();
        }
    }

    public static <E extends Module> E getModule(Class<? extends Module> clazz) {
        return (E) modules.get(clazz);
    }

    public static TitanPlugin getInstance() {
        return instance;
    }

}
