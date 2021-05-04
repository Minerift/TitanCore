package org.minerift.titan.modules.gemshop;

import org.bukkit.inventory.ItemStack;
import org.minerift.titan.modules.commissary.util.CommissaryTier;
import org.minerift.titan.modules.tools.CustomToolEnum;

import java.util.Random;

public enum GemShopOffer {

    // Donor Tools
    BOUNTIFUL_PICKAXE(
            new ItemStack(CustomToolEnum.BOUNTIFUL_PICKAXE.getTool()),
            50000
    ),
    EXPLOSIVE_PICKAXE(
            new ItemStack(CustomToolEnum.EXPLOSIVE_PICKAXE.getTool()),
            30000
    ),
    SMELTERS_PICKAXE(
            new ItemStack(CustomToolEnum.SMELTERS_PICKAXE.getTool()),
            10000
    ),

    // Discounted Commissary Tickets
    C1_TICKET(
            new ItemStack(CommissaryTier.C1.getTicketItem()),
            100
    ),
    C2_TICKET(
            new ItemStack(CommissaryTier.C2.getTicketItem()),
            200
    ),
    C3_TICKET(
            new ItemStack(CommissaryTier.C3.getTicketItem()),
            350
    );


    private ItemStack icon;
    private int cost;

    private GemShopOffer(ItemStack icon, int cost) {
        this.icon = icon;
        this.cost = cost;
    }

    public ItemStack getRawIcon() {
        return icon;
    }

    public ItemStack getIcon() {
        // TODO: add description
        return icon;
    }

    public int getCost() {
        return cost;
    }

    public static GemShopOffer random() {
        GemShopOffer[] values = GemShopOffer.values();
        return values[RANDOM.nextInt(values.length)];
    }

    private static final Random RANDOM = new Random();
}
