package org.vexar.titan.modules.gemshop.panel;

import org.avaeriandev.api.panel.IconScript;
import org.vexar.titan.VexarProfile;
import org.vexar.titan.modules.gemshop.GemShopManager;
import org.bukkit.entity.Player;

public class GemPurchaseScript implements IconScript {

    private int slot;

    public GemPurchaseScript(int slot) {
        this.slot = slot;
    }

    @Override
    public void run(Player plr) {

        VexarProfile vexarProfile = VexarProfile.get(plr);

        // Check whether player can afford slot item
        if(vexarProfile.getTickets() >= GemShopManager.dailyItems[slot].getCost()) {

        } else {

        }
    }
}
