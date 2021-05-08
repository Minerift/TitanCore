package org.minerift.titan.modules.gemshop.panel;

import org.avaeriandev.api.panel.IconScript;
import org.avaeriandev.api.util.BaseUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.minerift.titan.TitanPlugin;
import org.minerift.titan.TitanProfile;
import org.minerift.titan.modules.gemshop.GemShopModule;
import org.minerift.titan.modules.gemshop.offers.GemShopOffer;

public class PurchaseScript implements IconScript {

    private final GemShopModule MODULE = TitanPlugin.getModule(GemShopModule.class);

    private int offerIndex;

    public PurchaseScript(int offerIndex) {
        this.offerIndex = offerIndex;
    }

    @Override
    public void run(Player plr) {

        TitanProfile profile = TitanProfile.get(plr);
        GemShopOffer offer = MODULE.getOffers()[offerIndex];

        // Check if player has exceeded daily purchases
        if(!profile.canPurchase(offerIndex)) {
            plr.sendMessage(BaseUtils.chat("&cYou can't purchase more of this item. Come back later!"));
            return;
        }

        // Check if player has enough gems
        if(profile.getTickets() >= offer.getCost()) {

            // Deduct gems from player
            profile.setTickets(profile.getTickets() - offer.getCost());

            // Give player item
            offer.getScript().run(plr);

            // Update amount of purchases for slot offer
            int[] slotPurchases = profile.getSlotPurchases();
            slotPurchases[offerIndex]++;
            profile.setSlotPurchases(slotPurchases);

            // Notify player of purchase
            plr.sendMessage(BaseUtils.chat("&aThank you for your purchase!"));
            plr.playSound(plr.getLocation(), Sound.LEVEL_UP, 1F, 1.5F);

            // Refresh panel
            plr.openInventory(new GemShopPanel(plr).getPanel());

        } else {

            plr.sendMessage(BaseUtils.chat("&cYou don't have enough gems!"));

        }
    }
}
