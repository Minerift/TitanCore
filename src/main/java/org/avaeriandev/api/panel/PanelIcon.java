package org.avaeriandev.api.panel;

import org.avaeriandev.api.util.BaseUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class PanelIcon {

    private ItemStack icon;
    private IconScript script;

    public PanelIcon(String name, List<String> lore, ItemStack icon, IconScript script) {
        buildIcon(name, lore, icon);
        this.script = script;
    }

    public PanelIcon(String name, ItemStack icon, IconScript script) {
        buildIcon(name, icon);
        this.script = script;
    }

    public PanelIcon(ItemStack icon, IconScript script) {
        this.icon = icon;
        this.script = script;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public IconScript getScript() {
        return script;
    }

    private void buildIcon(String name, List<String> lore, ItemStack icon) {

        ItemMeta meta = icon.getItemMeta();

        meta.setDisplayName(BaseUtils.chat(name));
        meta.setLore(BaseUtils.lore(lore));

        icon.setItemMeta(meta);
        this.icon = icon;
    }

    private void buildIcon(String name, ItemStack icon) {
        ItemMeta meta = icon.getItemMeta();

        meta.setDisplayName(BaseUtils.chat(name));

        icon.setItemMeta(meta);
        this.icon = icon;
    }

}
