package org.avaeriandev.api.panel;

import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.titancore.TitanPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public abstract class Panel implements Listener {

    private Inventory panel;
    private Map<Integer, PanelIcon> layout;

    public Panel(String title, int rows) {
        this.panel = Bukkit.createInventory(null, rows * 9, BaseUtils.chat(title));
        setup();
    }

    public Panel(String title, InventoryType inventoryType) {
        this.panel = Bukkit.createInventory(null, inventoryType, BaseUtils.chat(title));
        setup();
    }

    private void setup() {
        this.layout = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, TitanPlugin.getInstance());
    }



}
