package org.avaeriandev.titancore.commissary;

import org.avaeriandev.api.panel.IconScript;
import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.titancore.TitanAPI;
import org.avaeriandev.titancore.TitanPlayer;
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

        TitanPlayer titanPlayer = TitanAPI.getTitanPlayer(plr);
        if(titanPlayer.getTickets() >= tier.getTicketPrice()) {

            // Purchase pass with tickets
            titanPlayer.setTickets(titanPlayer.getTickets() - tier.getTicketPrice());

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
