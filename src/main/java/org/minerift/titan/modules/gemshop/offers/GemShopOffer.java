package org.minerift.titan.modules.gemshop.offers;

import org.avaeriandev.api.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Explosive;
import org.bukkit.inventory.ItemStack;
import org.minerift.titan.modules.commissary.util.CommissaryTier;
import org.minerift.titan.modules.explosives.ExplosiveType;
import org.minerift.titan.modules.gemshop.offers.scripts.ItemOfferScript;
import org.minerift.titan.modules.gemshop.offers.scripts.MoneyOfferScript;
import org.minerift.titan.modules.gemshop.offers.scripts.OfferScript;
import org.minerift.titan.modules.tools.CustomToolEnum;

import java.util.Random;

public enum GemShopOffer {

    // Donor Tools
    BOUNTIFUL_PICKAXE(
            new ItemStack(CustomToolEnum.BOUNTIFUL_PICKAXE.getRawTool()),
            50000,
            new ItemOfferScript(new ItemStack(CustomToolEnum.BOUNTIFUL_PICKAXE.getWorkingTool()))
    ),
    EXPLOSIVE_PICKAXE(
            new ItemStack(CustomToolEnum.EXPLOSIVE_PICKAXE.getRawTool()),
            30000,
            new ItemOfferScript(new ItemStack(CustomToolEnum.EXPLOSIVE_PICKAXE.getWorkingTool()))
    ),
    SMELTERS_PICKAXE(
            new ItemStack(CustomToolEnum.SMELTERS_PICKAXE.getRawTool()),
            10000,
            new ItemOfferScript(new ItemStack(CustomToolEnum.SMELTERS_PICKAXE.getWorkingTool()))
    ),

    // Discounted Commissary Tickets
    C1_TICKET(
            new ItemStack(CommissaryTier.C1.getTicketItem()),
            100,
            new ItemOfferScript(new ItemStack(CommissaryTier.C1.getTicketItem()))
    ),
    C2_TICKET(
            new ItemStack(CommissaryTier.C2.getTicketItem()),
            200,
            new ItemOfferScript(new ItemStack(CommissaryTier.C2.getTicketItem()))
    ),
    C3_TICKET(
            new ItemStack(CommissaryTier.C3.getTicketItem()),
            350,
            new ItemOfferScript(new ItemStack(CommissaryTier.C3.getTicketItem()))
    ),

    // Dynamite
    X32_DYNAMITE(
            new ItemBuilder(new ItemStack(ExplosiveType.DYNAMITE.getRawItem()))
                    .setName("&8[&c&l32x DYNAMITE&8]")
                    .setAmount(32)
                    .create(),
            25,
            new ItemOfferScript(new ItemStack(ExplosiveType.DYNAMITE.getWorkingItem()))
    ),

    X64_DYNAMITE(
            new ItemBuilder(new ItemStack(ExplosiveType.DYNAMITE.getRawItem()))
                    .setName("&8[&c&l64x DYNAMITE&8]")
                    .setAmount(64)
                    .create(),
            50,
            new ItemOfferScript(new ItemStack(ExplosiveType.DYNAMITE.getWorkingItem()))
    ),

    // Money
    MONEY_100K(
            new ItemBuilder(new ItemStack(Material.PAPER))
                .setName("&8[&a&l$100,000&8]")
                .create(),
            100,
            new MoneyOfferScript(100000)
    ),
    MONEY_300K(
            new ItemBuilder(new ItemStack(Material.PAPER))
                    .setName("&8[&a&l$300,000&8]")
                    .create(),
            300,
            new MoneyOfferScript(300000)
    ),
    MONEY_500K(
            new ItemBuilder(new ItemStack(Material.PAPER))
                    .setName("&8[&a&l$500,000&8]")
                    .create(),
            500,
            new MoneyOfferScript(500000)
    )

    // VAULT KEYS; coming soon

    ;


    private ItemStack icon;
    private int cost;
    private OfferScript script;

    private GemShopOffer(ItemStack icon, int cost, OfferScript script) {
        this.icon = icon;
        this.cost = cost;
        this.script = script;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public OfferScript getScript() {
        return script;
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
