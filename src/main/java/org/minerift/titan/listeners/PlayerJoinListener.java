package org.minerift.titan.listeners;

import org.avaeriandev.api.util.BaseUtils;
import org.bukkit.Bukkit;
import org.minerift.titan.TitanPlugin;
import org.minerift.titan.TitanProfile;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.minerift.titan.modules.gemshop.GemShopModule;

public class PlayerJoinListener implements Listener {

    private final GemShopModule GEM_SHOP_MODULE = TitanPlugin.getModule(GemShopModule.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        // Load player data
        Player plr = e.getPlayer();
        TitanProfile profile = TitanProfile.load(plr);

        if(profile.getLocalGemShopVersion() != GEM_SHOP_MODULE.getVersion()) {
            profile.updateGemShopData();
            plr.sendMessage(BaseUtils.chat("&aWhile you were offline, the gem shop has restocked!"));
        }

        // Give player first-join bread (if eligible)
        if(!plr.hasPlayedBefore()) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + plr.getName() + " group add E4");
            plr.getInventory().addItem(new ItemStack(Material.BREAD, 5));
        }

    }

}
