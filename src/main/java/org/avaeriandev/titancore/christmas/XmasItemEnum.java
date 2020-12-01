package org.avaeriandev.titancore.christmas;

import org.avaeriandev.api.util.ItemBuilder;
import org.avaeriandev.api.util.SkullBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public enum XmasItemEnum {

    PRESENT(
        new SkullBuilder()
        .setOwner("ArreArri")
        .setName("&dMystery Gift")
        .setLore("&7Right click to open")
        .create()
    ),

    ORNAMENT(
        new ItemBuilder(new ItemStack(Material.MAGMA_CREAM))
        .setName("&6Ornament")
        .setLore()
        .addEnchant(Enchantment.ARROW_DAMAGE, 1, false)
        .addFlag(ItemFlag.HIDE_ENCHANTS)
        .create()
    ),

    SNOWFLAKE(
        new ItemBuilder(new ItemStack(Material.SNOW_BALL))
        .setName("&bSnowflake")
        .setLore()
        .addEnchant(Enchantment.ARROW_DAMAGE, 1, false)
        .addFlag(ItemFlag.HIDE_ENCHANTS)
        .create()
    ),

    TREE_STAR(
        new ItemBuilder(new ItemStack(Material.NETHER_STAR))
        .setName("&eChristmas Star")
        .setLore()
        .addEnchant(Enchantment.ARROW_DAMAGE, 1, false)
        .addFlag(ItemFlag.HIDE_ENCHANTS)
        .create()
    );

    private ItemStack item;
    XmasItemEnum(ItemStack item) {
        this.item = item;
    }

    public static XmasItemEnum random() {
        int random = new Random().nextInt(XmasItemEnum.values().length);
        return XmasItemEnum.values()[random];
    }

    public ItemStack getItem() {
        return item;
    }
}
