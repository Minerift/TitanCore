package org.avaeriandev.titancore.commissary;

import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.titancore.TitanPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.TimeUnit;

public class CommissarySignListener implements Listener {

    @EventHandler
    public void onSignClick(PlayerInteractEvent e) {

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK
                && e.getClickedBlock().getType() == Material.WALL_SIGN) {

            Player plr = e.getPlayer();
            Location signLocation = e.getClickedBlock().getLocation();

            for(CommissaryTier tier : CommissaryTier.values()) {
                if(tier.getSignLocation().equals(signLocation)) {

                    if(plr.getItemInHand().isSimilar(tier.getTicketItem())) {

                        // Deduct item from hand
                        if(plr.getItemInHand().getAmount() > 1) {
                            plr.getItemInHand().setAmount(plr.getItemInHand().getAmount() - 1);
                        } else {
                            plr.setItemInHand(new ItemStack(Material.AIR, 1));
                        }

                        // Allow person through, create timer for that person
                        plr.teleport(tier.getInteriorLocation());
                        plr.sendMessage(BaseUtils.chat("&aYou have entered commissary for 120 seconds."));

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                plr.performCommand("spawn");
                                plr.sendMessage(BaseUtils.chat("&aTime's up!"));
                            }
                        }.runTaskLater(TitanPlugin.getInstance(), 20 * TimeUnit.MINUTES.toSeconds(2));


                    } else {
                        // Notify player they need to hold the correct item
                        plr.sendMessage(BaseUtils.chat("&cYou need to be holding a " + tier.name() + " ticket!"));
                    }

                    return;
                }
            }
        }
    }
}
