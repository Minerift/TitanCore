package org.avaeriandev.titancore.enchanter;

import org.avaeriandev.api.panel.Panel;
import org.avaeriandev.api.panel.PanelIcon;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EnchanterPanel extends Panel {

    private static final String title = "Enchanter";
    private static final int rows = 1;

    public EnchanterPanel() {
        super(title, rows);
        setLayout();
    }

    private void setLayout() {

        Map<Integer, PanelIcon> layout = new HashMap<>();

        layout.put(1, new PanelIcon(
            "&5Enchants 1 - 6",
            Arrays.asList("&2Enchant levels 1 - 6", "&4Costs 1 ticket per level"),
            new ItemStack(Material.PAPER, 1),
            new EnchantScript(EnchantLevel.L1_L6))
        );

        layout.put(3, new PanelIcon(
            "&5Enchants 7 - 12",
            Arrays.asList("&2Enchant levels 7 - 12", "&4Costs 1 ticket per level"),
            new ItemStack(Material.PAPER, 1),
            new EnchantScript(EnchantLevel.L7_L12))
        );

        layout.put(5, new PanelIcon(
            "&5Enchants 13 - 18",
            Arrays.asList("&2Enchant levels 13 - 18", "&4Costs 1 ticket per level"),
            new ItemStack(Material.PAPER, 1),
            new EnchantScript(EnchantLevel.L13_L18))
        );

        layout.put(7, new PanelIcon(
            "&5Enchants 19 - 24",
            Arrays.asList("&2Enchant levels 19 - 24", "&4Costs 1 ticket per level"),
            new ItemStack(Material.PAPER, 1),
            new EnchantScript(EnchantLevel.L19_L24))
        );

        layout.put(9, new PanelIcon(
            "&5Enchants 25 - 30",
            Arrays.asList("&2Enchant levels 25 - 30", "&4Costs 1 ticket per level"),
            new ItemStack(Material.PAPER, 1),
            new EnchantScript(EnchantLevel.L25_L30))
        );

        super.loadLayout(layout);

    }
}
