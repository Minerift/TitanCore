package org.avaeriandev.api.panel;

import org.avaeriandev.api.util.BaseUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public abstract class Panel {

    private Inventory panel;
    private Map<Integer, PanelIcon> layout;

    public Panel(String title, int rows) {
        this.panel = Bukkit.createInventory(null, rows * 9, BaseUtils.chat(title));
        this.layout = new HashMap<>();
    }

    public Panel(String title, InventoryType inventoryType) {
        this.panel = Bukkit.createInventory(null, inventoryType, BaseUtils.chat(title));
    }

}
