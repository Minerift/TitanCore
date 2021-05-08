package org.minerift.titan.modules.explosives;

import de.tr7zw.nbtapi.NBTItem;
import org.avaeriandev.api.util.ItemBuilder;
import org.minerift.titan.modules.explosives.handlers.DynamiteHandler;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ExplosiveType {

    DYNAMITE(
        new ItemBuilder(new ItemStack(Material.TNT))
            .setName("&8[&c&lDYNAMITE&8]")
            .create(),
        3F,
        new DynamiteHandler()
    ),

    /*INFINIMITE(
        new ItemBuilder(new ItemStack(Material.TNT))
            .setName("&8[&c&lINFINIMITE&8]")
            .create(),
            5F,
        new DynamiteHandler()
    )*/

    // Cluster bomb
    // Atom bomb

    ;


    private ItemStack item;
    private float radius;
    private ExplosiveHandler handler;
    ExplosiveType(ItemStack item, float radius, ExplosiveHandler handler) {
        this.item = item;
        this.radius = radius;
        this.handler = handler;
    }

    public ItemStack getRawItem() {
        return item;
    }

    public ItemStack getWorkingItem() {
        NBTItem nbtItem = new NBTItem(new ItemStack(this.item));
        nbtItem.setString("explosive", this.name());

        return nbtItem.getItem();
    }

    public ExplosiveHandler getHandler() {
        return handler;
    }

    public float getRadius() {
        return radius;
    }

}
