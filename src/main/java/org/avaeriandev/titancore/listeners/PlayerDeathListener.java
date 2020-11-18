package org.avaeriandev.titancore.listeners;

import org.bukkit.Material;
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
            if(random.nextInt(10) > 7) {
                ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                attacker.getInventory().addItem(skull);
            }
        }
    }
}
