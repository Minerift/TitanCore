package org.avaeriandev.titancore.listeners;

import de.tr7zw.nbtapi.NBTItem;
import org.avaeriandev.titancore.TitanPlugin;
import org.avaeriandev.titancore.christmas.XmasItemEnum;
import org.avaeriandev.titancore.christmas.giftrewards.*;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class OpenPresentListener implements Listener {

    private List<GiftReward> possibleRewards;

    public OpenPresentListener() {
        possibleRewards = new ArrayList<>();

        // Gems
        possibleRewards.add(new GemGiftReward(100));
        possibleRewards.add(new GemGiftReward(200));
        possibleRewards.add(new GemGiftReward(300));

        // Money
        possibleRewards.add(new MoneyGiftReward(15000));
        possibleRewards.add(new MoneyGiftReward(30000));
        possibleRewards.add(new MoneyGiftReward(50000));

        // Coal
        possibleRewards.add(new CoalGiftReward(4, 16));
        possibleRewards.add(new CoalGiftReward(4, 16));
        possibleRewards.add(new CoalGiftReward(4, 16));

        // Xmas Items
        possibleRewards.add(new XmasItemGiftReward(XmasItemEnum.ORNAMENT, 1, 4));
        possibleRewards.add(new XmasItemGiftReward(XmasItemEnum.ORNAMENT, 1, 4));
        possibleRewards.add(new XmasItemGiftReward(XmasItemEnum.ORNAMENT, 1, 4));
        possibleRewards.add(new XmasItemGiftReward(XmasItemEnum.ORNAMENT, 1, 4));

        possibleRewards.add(new XmasItemGiftReward(XmasItemEnum.SNOWFLAKE, 1, 4));
        possibleRewards.add(new XmasItemGiftReward(XmasItemEnum.SNOWFLAKE, 1, 4));
        possibleRewards.add(new XmasItemGiftReward(XmasItemEnum.SNOWFLAKE, 1, 4));
        possibleRewards.add(new XmasItemGiftReward(XmasItemEnum.SNOWFLAKE, 1, 4));

        possibleRewards.add(new XmasItemGiftReward(XmasItemEnum.TREE_STAR, 1, 4));
        possibleRewards.add(new XmasItemGiftReward(XmasItemEnum.TREE_STAR, 1, 4));
        possibleRewards.add(new XmasItemGiftReward(XmasItemEnum.TREE_STAR, 1, 4));
        possibleRewards.add(new XmasItemGiftReward(XmasItemEnum.TREE_STAR, 1, 4));
        
    }

    @EventHandler
    public void onPresentOpen(PlayerInteractEvent e) {

        Player plr = e.getPlayer();

        // Verify action is right click
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) return;

        // Verify whether item is indeed a present
        if(plr.getItemInHand() == null || plr.getItemInHand().getType() == Material.AIR) return;
        NBTItem nbtItem = new NBTItem(plr.getItemInHand());
        if(!nbtItem.hasKey("XmasItem") || !nbtItem.getString("XmasItem").equalsIgnoreCase(XmasItemEnum.PRESENT.name())) return;

        // Remove item
        e.setCancelled(true);
        removeItemInHand(plr);
        plr.playSound(plr.getLocation(), Sound.ENDERDRAGON_WINGS, 1F, 1.5F);

        new BukkitRunnable() {
            @Override
            public void run() {

                int random = ThreadLocalRandom.current().nextInt(possibleRewards.size());
                possibleRewards.get(random).rewardPlayer(plr);

            }
        }.runTaskLater(TitanPlugin.getInstance(), 20L * TimeUnit.SECONDS.toSeconds(1));
    }

    private void removeItemInHand(Player plr) {
        ItemStack item = plr.getItemInHand();
        if(item.getAmount() > 1) {
            item.setAmount(item.getAmount() - 1);
        } else {
            plr.setItemInHand(new ItemStack(Material.AIR));
            plr.updateInventory();
        }
    }

}
