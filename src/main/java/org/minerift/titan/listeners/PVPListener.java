package org.minerift.titan.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.List;

public class PVPListener implements Listener {

    @EventHandler
    public void onPlayerAttackPlayer(EntityDamageByEntityEvent e) {

        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {

            Player victim = (Player) e.getEntity();
            Player attacker = (Player) e.getDamager();

            List<Material> bloxUnderVictim = new ArrayList<>();
            List<Material> bloxUnderAttacker = new ArrayList<>();
            for(int i = 0; i < 5; i++) {
                Location underPlrVictim = victim.getLocation().subtract(0, (i), 0);
                Location underPlrAttacker = attacker.getLocation().subtract(0, (i), 0);
                bloxUnderVictim.add(underPlrVictim.getBlock().getType());
                bloxUnderAttacker.add(underPlrAttacker.getBlock().getType());
            }
            for(int i = 0; i < 5; i++) {
                if(!(bloxUnderVictim.contains(Material.BEDROCK) && bloxUnderAttacker.contains(Material.BEDROCK))) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
