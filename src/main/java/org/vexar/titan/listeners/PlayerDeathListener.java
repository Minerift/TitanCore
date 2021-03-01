package org.vexar.titan.listeners;

import de.tr7zw.nbtapi.NBTItem;
import org.avaeriandev.api.util.SkullBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(EntityDeathEvent e) {

        if(e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player) {

            Player victim = (Player) e.getEntity();
            Player attacker = (Player) e.getEntity().getKiller();

            Random random = new Random();
            if(random.nextInt(10) >= 7) {
                // Build item
                ItemStack skull = new SkullBuilder().setOwner(victim.getName()).create();
                NBTItem nbtItem = new NBTItem(skull);
                nbtItem.setBoolean("quest-skull", true);

                // Add item to attacker's inventory
                attacker.getInventory().addItem(nbtItem.getItem());
            }
        }
    }
}
