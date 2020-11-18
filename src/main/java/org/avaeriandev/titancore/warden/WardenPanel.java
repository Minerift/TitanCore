package org.avaeriandev.titancore.warden;

import org.avaeriandev.api.panel.IconScript;
import org.avaeriandev.api.panel.Panel;
import org.avaeriandev.api.panel.PanelIcon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WardenPanel extends Panel {

    private static final String title = "RankUp";
    private static final int rows = 1;

    public WardenPanel() {
        super(title, rows);
        setLayout();
    }

    private void setLayout() {

        Map<Integer, PanelIcon> layout = new HashMap<>();

        // Confirm
        layout.put(1, new PanelIcon("&aConfirm", new ItemStack(Material.WOOL, 1, (byte) 5), new IconScript() {
            @Override
            public void run(Player plr) {
                plr.performCommand("rankup");
            }
        }));

        List<String> infoLore = new ArrayList<>();
        infoLore.add("&4Make sure your cell is empty.");
        infoLore.add("&4Cells will be cleared when ranking");
        infoLore.add("&4up to a new ward.     E1 -> D4");

        layout.put(5, new PanelIcon("&5RankUp", infoLore, new ItemStack(Material.PAPER, 1), null));

        // Cancel
        layout.put(9, new PanelIcon("&cCancel", new ItemStack(Material.WOOL, 1, (byte) 14), new IconScript() {
            @Override
            public void run(Player plr) {
                plr.closeInventory();
            }
        }));

        super.loadLayout(layout);

    }

}
