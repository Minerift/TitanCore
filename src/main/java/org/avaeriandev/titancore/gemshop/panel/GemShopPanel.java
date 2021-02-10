package org.avaeriandev.titancore.gemshop.panel;

import org.avaeriandev.api.panel.IconScript;
import org.avaeriandev.api.panel.Panel;
import org.avaeriandev.api.panel.PanelIcon;
import org.avaeriandev.api.util.ItemBuilder;
import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.TitanPlayerAPI;
import org.avaeriandev.titancore.gemshop.GemShopManager;
import org.avaeriandev.titancore.gemshop.ShopItemEnum;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class GemShopPanel extends Panel {

    private static final String title = "Gem Shop";
    private static final int rows = 3;

    public GemShopPanel(Player plr) {
        super(title, rows);
        prepareLayout(plr);
    }

    private void prepareLayout(Player plr) {

        // Retrieve data
        TitanPlayer titanPlayer = TitanPlayerAPI.get(plr);

        int[] slotPurchases = titanPlayer.getSlotPurchases();

        // Build layout
        Map<Integer, PanelIcon> layout = new HashMap<>();

        layout.put(1, buildSlotIcon(0));
        layout.put(2, buildSlotIcon(1));
        layout.put(3, buildSlotIcon(2));

    }

    private PanelIcon buildSlotIcon(int shopSlot) {
        ShopItemEnum item = GemShopManager.dailyItems[shopSlot];
        return new PanelIcon(item.getIcon(), new GemPurchaseScript(shopSlot));
    }
}
