package org.avaeriandev.titancore;

import org.avaeriandev.titancore.api.external.Placeholders;
import org.avaeriandev.titancore.auction.AuctionListing;
import org.avaeriandev.titancore.auction.AuctionSystem;
import org.avaeriandev.titancore.auction.AuctionSignListener;
import org.avaeriandev.titancore.commands.*;
import org.avaeriandev.titancore.commissary.CommissaryCommand;
import org.avaeriandev.titancore.commissary.CommissarySignListener;
import org.avaeriandev.titancore.enchanter.EnchanterCommand;
import org.avaeriandev.titancore.listeners.*;
import org.avaeriandev.titancore.quests.QuestCommand;
import org.avaeriandev.titancore.tools.ToolCommand;
import org.avaeriandev.titancore.warden.WardenCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TitanPlugin extends JavaPlugin {

    private static TitanPlugin instance;
    private static BukkitTask questStateCheckTimer;

    public static File plrDataDir;

    public void onEnable() {
        instance = this;
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

        // Register auction listeners
        registerListener(new AuctionSystem());
        registerListener(new AuctionSignListener());

        AuctionSystem.load();

        // Register other listeners
        registerListener(new PlayerJoinListener());
        registerListener(new PlayerQuitListener());
        registerListener(new BlockBreakListener());
        registerListener(new PVPListener());
        registerListener(new PlayerDeathListener());
        registerListener(new OpenPresentListener());

        registerListener(new CommissarySignListener());

        // Register commands
        registerCommand("commissary", new CommissaryCommand());
        registerCommand("warden", new WardenCommand());
        registerCommand("enchanter", new EnchanterCommand());
        registerCommand("ticket", new TicketCommand());
        registerCommand("quest", new QuestCommand());
        registerCommand("fw", new FireworksCommand());
        registerCommand("rifttool", new ToolCommand());
        registerCommand("xmas", new XmasItemCommand());

        registerCommand("speed", new SpeedCommand());
        registerCommand("haste", new HasteCommand());

        // Register placeholders
        new Placeholders().register();

    }

    public void onDisable() {

        questStateCheckTimer.cancel();

        // Save player data
        for(Player plr : Bukkit.getOnlinePlayers()) {
            TitanPlayerAPI.save(plr);
            TitanPlayerAPI.unload(plr);
        }

        // Delete auction chests
        for(AuctionListing listing : new ArrayList<>(AuctionSystem.listings)) {
            if (listing.isOnDeletionTimer()) {
                listing.getDeletionTimer().cancel();
                listing.delete();
            }
        }

        AuctionSystem.save();
    }

    private void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
    private void registerCommand(String prefix, CommandExecutor executor) {
        instance.getCommand(prefix).setExecutor(executor);
    }

    public static TitanPlugin getInstance() {
        return instance;
    }

}
