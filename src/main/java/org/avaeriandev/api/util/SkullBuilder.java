package org.avaeriandev.api.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullBuilder extends ItemBuilder {

    public SkullBuilder() {
        super(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3));
    }

    public SkullBuilder setOwner(String username) {
        ((SkullMeta) meta).setOwner(username);
        return this;
    }

    @Override
    public SkullBuilder setName(String name) {
        super.setName(name);
        return this;
    }

    @Override
    public SkullBuilder setLore(String... lines) {
        super.setLore(lines);
        return this;
    }

    @Override
    public SkullBuilder setAmount(int amount) {
        super.setAmount(amount);
        return this;
    }

    @Override
    public SkullBuilder addEnchant(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        super.addEnchant(enchantment, level, ignoreLevelRestriction);
        return this;
    }

    @Override
    public SkullBuilder addFlag(ItemFlag... itemFlags) {
        super.addFlag(itemFlags);
        return this;
    }

    @Override
    public SkullBuilder setUnbreakable(boolean unbreakable) {
        super.setUnbreakable(unbreakable);
        return this;
    }

    @Override
    public ItemMeta getItemMeta() {
        return super.getItemMeta();
    }

    @Override
    public ItemStack create() {
        item.setItemMeta((SkullMeta) meta);
        return item;
    }
}
