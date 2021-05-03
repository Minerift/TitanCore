package org.minerift.titan.modules;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.minerift.titan.TitanPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Module {

    private Map<String, CommandExecutor> commands = new HashMap<>();
    private List<Listener> listeners;

    private boolean isEnabled = false;

    protected abstract void onEnable();
    protected abstract void onDisable();

    public void enable() {

        this.listeners = new ArrayList<>();
        this.isEnabled = true;

        onEnable();
    }

    public void disable() {

        listeners.forEach(listener -> HandlerList.unregisterAll(listener));
        this.isEnabled = false;

        onDisable();
    }

    protected void registerCommand(String prefix, CommandExecutor executor) {
        if(!commands.containsKey(prefix)) {
            TitanPlugin.getInstance().getCommand(prefix).setExecutor(executor);
            commands.put(prefix, executor);
        }
    }

    protected void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, TitanPlugin.getInstance());
        listeners.add(listener);
    }

    public boolean isEnabled() {
        return isEnabled;
    }

}
