package org.avaeriandev.titancore.modules.explosives;

import org.avaeriandev.api.util.ItemBuilder;
import org.avaeriandev.titancore.modules.explosives.handlers.DynamiteHandler;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ExplosiveType {

    DYNAMITE(
        new ItemBuilder(new ItemStack(Material.TNT))
            .setName("&cDynamite")
            .create(),
        3F,
        new DynamiteHandler()
    );


    private ItemStack item;
    private float radius;
    private ExplosiveHandler handler;
    ExplosiveType(ItemStack item, float radius, ExplosiveHandler handler) {
        this.item = item;
        this.radius = radius;
        this.handler = handler;
    }

    public ItemStack getItem() {
        return item;
    }

    public ExplosiveHandler getHandler() {
        return handler;
    }

    public float getRadius() {
        return radius;
    }

}
