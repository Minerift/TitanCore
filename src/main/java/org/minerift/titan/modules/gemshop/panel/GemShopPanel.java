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
import org.minerift.titan.modules.gemshop.offers.GemShopOffer;

import java.util.*;

public class GemShopPanel extends Panel {

    private static final GemShopModule MODULE = TitanPlugin.getModule(GemShopModule.class);
    private static final String TITLE = "&8Gem Shop";
    private static final int ROWS = 3;

    private static final int[] OFFER_SLOTS = {12, 14, 16};
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

        // Left row design
        layout.put(1, new PanelIcon(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15), null));
        layout.put(10, new PanelIcon(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15), null));
        layout.put(19, new PanelIcon(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15), null));

        // Right row design
        layout.put(9, new PanelIcon(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15), null));
        layout.put(18, new PanelIcon(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15), null));
        layout.put(27, new PanelIcon(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15), null));

        // Add offers to panel
        for(int i = 0; i < OFFER_SLOTS.length; i++) {
            int slot = OFFER_SLOTS[i];
            layout.put(slot, new PanelIcon(profile.canPurchase(i) ? buildOfferIcon(MODULE.getOffers()[i], profile) : new ItemStack(ERROR_ICON), new PurchaseScript(i)));
        }

        super.loadLayout(layout);
    }

    private ItemStack buildOfferIcon(GemShopOffer offer, TitanProfile profile) {

        ItemBuilder icon = new ItemBuilder(new ItemStack(offer.getIcon()));

        List<String> lore = icon.getItemMeta().hasLore() ? new ArrayList<>(icon.getItemMeta().getLore()) : new ArrayList<>();
        lore.add("");
        lore.add("&fYour gems: &a&n⧫" + profile.getTickets());
        lore.add("&fCost: &2&n⧫" + offer.getCost());
        lore.add("");
        lore.add("&8&oThe Gem Shop is restocked every 24 hours.");
        lore.add("&8&oEach offer can be purchased 3 times per day.");

        icon.setLore(lore);
        return icon.create();
    }
}
