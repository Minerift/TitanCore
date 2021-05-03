package org.minerift.titan.modules.tools;

import me.clip.ezblocks.EZBlocks;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.minerift.titan.TitanProfile;

import java.util.ArrayList;
import java.util.List;

public abstract class MagnetHandler {

    protected TitanProfile titanProfile;

    public MagnetHandler(TitanProfile titanProfile) {
        this.titanProfile = titanProfile;
    }

    // Handle magnet default
    public void handleMagnet(Block block, boolean countForGems) {
        if(titanProfile.hasMagnetAbility() && titanProfile.isMagnetActive()) {
            useMagnetHandler(block, countForGems);
        } else {
            useDefaultHandler(block, countForGems);
        }
    }

    // Handle custom drops
    public void handleMagnet(Block block, List<ItemStack> handledDrops, boolean countForGems) {
        if(titanProfile.hasMagnetAbility() && titanProfile.isMagnetActive()) {
            useMagnetHandler(block, handledDrops, countForGems);
        } else {
            useDefaultHandler(block, handledDrops, countForGems);
        }
    }

    // Handle magnet with a tool
    public void handleMagnet(Block block, ItemStack toolUsed, boolean countForGems) {
        if(titanProfile.hasMagnetAbility() && titanProfile.isMagnetActive()) {
            useMagnetHandler(block, toolUsed, countForGems);
        } else {
            useDefaultHandler(block, toolUsed, countForGems);
        }
    }

    private void useMagnetHandler(Block block, boolean countForGems) {
        useMagnetHandler(block, new ArrayList<>(block.getDrops()), countForGems);
    }

    private void useMagnetHandler(Block block, ItemStack toolUsed, boolean countForGems) {
        useMagnetHandler(block, new ArrayList<>(block.getDrops(toolUsed)), countForGems);
    }

    private void useMagnetHandler(Block block, List<ItemStack> customDrops, boolean countForGems) {
        Player plr = titanProfile.getPlayer().getPlayer();
        Inventory inventory = plr.getInventory();

        block.setType(Material.AIR);
        for(ItemStack drop : customDrops) {
            inventory.addItem(drop);
        }

        if(countForGems) {
            EZBlocks.getEZBlocks().getBreakHandler().handleBlockBreakEvent(plr, block);
        }
    }

    private void useDefaultHandler(Block block, boolean countForGems) {
        useDefaultHandler(block, new ArrayList<>(block.getDrops()), countForGems);
    }

    private void useDefaultHandler(Block block, ItemStack toolUsed, boolean countForGems) {
        useDefaultHandler(block, new ArrayList<>(block.getDrops(toolUsed)), countForGems);
    }

    protected abstract void useDefaultHandler(Block block, List<ItemStack> customDrops, boolean countForGems);

}
