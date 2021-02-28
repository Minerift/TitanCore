package org.avaeriandev.titancore.modules.gemshop.panel;

import org.avaeriandev.api.panel.IconScript;
import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.TitanPlayerAPI;
import org.avaeriandev.titancore.modules.gemshop.GemShopManager;
import org.bukkit.entity.Player;

public class GemPurchaseScript implements IconScript {

    private int slot;

    public GemPurchaseScript(int slot) {
        this.slot = slot;
    }

    @Override
    public void run(Player plr) {

        TitanPlayer titanPlayer = TitanPlayer.get(plr);

        // Check whether player can afford slot item
        if(titanPlayer.getTickets() >= GemShopManager.dailyItems[slot].getCost()) {

        } else {

        }
    }
}
