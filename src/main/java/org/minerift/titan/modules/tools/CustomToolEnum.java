package org.minerift.titan.modules.tools;

import org.avaeriandev.api.util.ItemBuilder;
import org.minerift.titan.modules.tools.handlers.*;
import org.vexar.titan.modules.tools.handlers.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum CustomToolEnum {

    BOUNTIFUL_PICKAXE(
        new ItemBuilder(new ItemStack(Material.DIAMOND_PICKAXE, 1))
            .setName("&bBountiful Pickaxe")
            .setLore("Drop the ores from others nearby!")
            .setUnbreakable(true)
            .create(),
        new BountifulHandler()
    ),

    EXPLOSIVE_PICKAXE(
        new ItemBuilder(new ItemStack(Material.DIAMOND_PICKAXE, 1))
            .setName("&bExplosive Pickaxe")
            .setLore("Blast your way to the top!")
            .setUnbreakable(true)
            .create(),
        new ExplosiveHandler()
    ),

    SMELTERS_PICKAXE(
        new ItemBuilder(new ItemStack(Material.DIAMOND_PICKAXE, 1))
            .setName("&bSmelter's Pickaxe")
            .setLore("A trusty pick that smelts what you mine!")
            .setUnbreakable(true)
            .create(),
        new SmelterHandler()
    ),

    LUMBERJACK_AXE(
        new ItemBuilder(new ItemStack(Material.DIAMOND_AXE, 1))
            .setName("&bLumberjack's Axe")
            .setLore("Extra Sharp!")
            .setUnbreakable(true)
            .create(),
        new LumberjackHandler()
    );

    private ItemStack tool;
    private AbstractHandler handler;
    CustomToolEnum(ItemStack tool, AbstractHandler handler) {
        this.tool = tool;
        this.handler = handler;
    }

    public ItemStack getTool() {
        return tool;
    }

    public AbstractHandler getHandler() {
        return handler;
    }

}
