package org.minerift.titan.modules.gemshop.panel;

import org.avaeriandev.api.panel.Panel;
import org.avaeriandev.api.panel.PanelIcon;
import org.avaeriandev.api.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.minerift.titan.TitanPlugin;
import org.minerift.titan.TitanProfile;
import org.minerift.titan.modules.gemshop.GemShopModule;

import java.util.HashMap;
import java.util.Map;

public class GemShopPanel extends Panel {

    private static final GemShopModule MODULE = TitanPlugin.getModule(GemShopModule.class);
    private static final String TITLE = "&8Gem Shop";
    private static final int ROWS = 3;

    private static final int[] OFFER_SLOTS = {11, 14, 17};
    private static final ItemStack ERROR_ICON = new ItemBuilder(new ItemStack(Material.BARRIER))
            .setName("&cYou can't purchase more of this item!")
            .create();

    public GemShopPanel(Player plr) {
        super(TITLE, ROWS);
        prepareLayout(plr);
    }

    private void prepareLayout(Player plr) {

        TitanProfile profile = TitanProfile.get(plr);
        Map<Integer, PanelIcon> layout = new HashMap<>();

        // Filler item
        for(int i = 0; i < getPanel().getSize(); i++) {
            layout.put(i + 1, new PanelIcon(new ItemStack(Material.STAINED_GLASS_PANE), null));
        }

        // Add offers to panel
        for(int i = 0; i < OFFER_SLOTS.length; i++) {
            int slot = OFFER_SLOTS[i];
            layout.put(slot, new PanelIcon(profile.canPurchase(i) ? MODULE.getOffers()[i].getIcon() : new ItemStack(ERROR_ICON), null));
        }

        super.loadLayout(layout);
    }
}
