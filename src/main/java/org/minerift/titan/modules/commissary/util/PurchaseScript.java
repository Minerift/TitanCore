package org.minerift.titan.modules.commissary.util;

import org.avaeriandev.api.panel.IconScript;
import org.avaeriandev.api.util.BaseUtils;
import org.minerift.titan.TitanProfile;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PurchaseScript implements IconScript {

    private CommissaryTier tier;

    public PurchaseScript(CommissaryTier tier) {
        this.tier = tier;
    }

    @Override
    public void run(Player plr) {

        TitanProfile titanProfile = TitanProfile.get(plr);
        if(titanProfile.getTickets() >= tier.getTicketPrice()) {

            // Purchase pass with tickets
            titanProfile.setTickets(titanProfile.getTickets() - tier.getTicketPrice());

            // Give commissary item to player
            plr.getInventory().addItem(new ItemStack(tier.getTicketItem()));

            // Notify player
            plr.sendMessage(BaseUtils.chat("&aYou purchased a " + tier.name() + " successfully!"));
            plr.playSound(plr.getLocation(), Sound.ORB_PICKUP, 1, 1);
        } else {
            // Notify player
            plr.sendMessage(BaseUtils.chat("&cYou don't have enough tickets!"));
            plr.playSound(plr.getLocation(), Sound.NOTE_PLING, 1, 0.5F);
        }
    }
}
