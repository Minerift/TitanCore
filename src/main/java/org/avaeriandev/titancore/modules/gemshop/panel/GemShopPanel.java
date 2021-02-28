package org.avaeriandev.titancore.modules.gemshop.panel;

import org.avaeriandev.api.panel.Panel;
import org.avaeriandev.api.panel.PanelIcon;
import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.TitanPlayerAPI;
import org.avaeriandev.titancore.modules.gemshop.GemShopManager;
import org.avaeriandev.titancore.modules.gemshop.ShopItemEnum;
import org.bukkit.entity.Player;

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
        TitanPlayer titanPlayer = TitanPlayer.get(plr);

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
