package org.minerift.titan.modules.explosives;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import de.tr7zw.nbtapi.NBTItem;
import org.minerift.titan.TitanPlugin;
import org.minerift.titan.modules.explosives.packets.NamedSoundEffectPacket;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class ExplosiveListener implements Listener {

    private final ProtocolManager manager = ProtocolLibrary.getProtocolManager();

    private Location recentDynamiteLocation;

    public ExplosiveListener() {
        this.recentDynamiteLocation = null;
        manager.addPacketListener(new PacketAdapter(
                TitanPlugin.getInstance(),
                ListenerPriority.NORMAL,
                PacketType.Play.Server.NAMED_SOUND_EFFECT) {
            @Override
            public void onPacketSending(PacketEvent event) {
                if(event.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT) {

                    // Get packet to read from
                    NamedSoundEffectPacket packet = new NamedSoundEffectPacket(event.getPacket());

                    // Confirm that sound is explosion sound
                    if(!packet.getSoundEffect().equalsIgnoreCase("random.explode")) { return; }

                    // Compare locations to determine whether the explosion originates from the entity
                    Location soundLocation = new Location(event.getPlayer().getWorld(), packet.getEffectPositionX() / 8, packet.getEffectPositionY() / 8, packet.getEffectPositionZ() / 8);

                    // Check if difference exceeds range
                    Vector difference = soundLocation.toVector().subtract(recentDynamiteLocation.toVector());
                    int range = 3;
                    if(Math.abs(difference.getX()) > range
                    || Math.abs(difference.getY()) > range
                    || Math.abs(difference.getZ()) > range) { return; }

                    // Check if sound is from explosive (and not from custom handler)
                    if(Math.abs(difference.getX()) % 1 != 0
                    || Math.abs(difference.getY()) % 1 != 0
                    || Math.abs(difference.getZ()) % 1 != 0) { return; }

                    // Cancel packet
                    event.setCancelled(true);
                }
            }
        });
    }

    // Update radius
    @EventHandler
    public void onDynamitePrime(ExplosionPrimeEvent e) {

        Entity entity = e.getEntity();

        // Determine whether entity is a custom explosive
        if(!isCustomExplosive(entity)) { return; }

        // Get custom explosive variation
        ExplosiveType explosiveType = getExplosiveType(entity);
        if(explosiveType == null) { return; }

        // Update radius
        e.setRadius(explosiveType.getRadius());

        // Cancel sound to handle in custom explosion
        // This is hacky as shit but there's no better method
        this.recentDynamiteLocation = entity.getLocation().getBlock().getLocation();
    }

    // Handle explosion
    @EventHandler
    public void onDynamiteExplode(EntityExplodeEvent e) {

        Entity entity = e.getEntity();

        // Determine whether entity is a custom explosive
        if(!isCustomExplosive(entity)) { return; }

        // Get explosive type
        ExplosiveType explosiveType = getExplosiveType(entity);
        if(explosiveType == null) { return; }

        // Handle custom explosion
        explosiveType.getHandler().handleExplosion(e, recentDynamiteLocation, getExplosiveThrower(entity));

    }

    @EventHandler
    public void onThrowDynamite(PlayerInteractEvent e) {

        Player plr = e.getPlayer();

        // Handle action
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) { return; }

        // Read NBT data on item to determine whether it is a custom explosive
        if(e.getItem() == null) { return; }
        NBTItem nbtItem = new NBTItem(e.getItem());
        if(!nbtItem.hasKey("explosive")) { return; }

        // Get explosive type
        ExplosiveType explosiveType = getExplosiveType(nbtItem.getString("explosive"));
        if(explosiveType == null) { return; }

        // Attach metadata to explosive entity
        Entity explosive = plr.getWorld().spawnEntity(plr.getEyeLocation(), EntityType.PRIMED_TNT);
        explosive.setMetadata("explosive", new FixedMetadataValue(TitanPlugin.getInstance(), explosiveType.name()));
        explosive.setMetadata("thrower", new FixedMetadataValue(TitanPlugin.getInstance(), plr));
        ((TNTPrimed) explosive).setVelocity(plr.getLocation().getDirection());
        ((TNTPrimed) explosive).setFuseTicks(20);

        // Deduct item from inventory
        if(plr.getItemInHand() != null && plr.getItemInHand().getType() != Material.AIR) {
            if(plr.getItemInHand().getAmount() > 1) {
                plr.getItemInHand().setAmount(plr.getItemInHand().getAmount() - 1);
            } else {
                plr.setItemInHand(new ItemStack(Material.AIR, 1));
            }
        }
    }

    @EventHandler
    public void onIllegalDynamitePlace(BlockPlaceEvent e) {

        NBTItem nbtItem = new NBTItem(e.getItemInHand());
        if(nbtItem.hasKey("explosive")) { e.setCancelled(true); }
    }

    @EventHandler
    public void onExplosionEntityDamage(EntityDamageByEntityEvent e) {

        Entity dynamite = e.getDamager();

        // Verify that attacker entity is from primed TNT
        if(dynamite.getType() != EntityType.PRIMED_TNT) { return; }

        // Verify that primed TNT is dynamite
        if(!dynamite.hasMetadata("explosive")) { return; }

        e.setCancelled(true);
    }

    private boolean isCustomExplosive(Entity entity) {
        return entity.hasMetadata("explosive");
    }

    private ExplosiveType getExplosiveType(Entity entity) {
        return getExplosiveType(entity.getMetadata("explosive").get(0).asString());
    }

    private ExplosiveType getExplosiveType(String value) {
        ExplosiveType explosive;
        try {
            explosive = ExplosiveType.valueOf(value);
        } catch(IllegalArgumentException e) {
            explosive = null;
        }
        return explosive;
    }

    private Player getExplosiveThrower(Entity entity) {
        return (Player) entity.getMetadata("thrower").get(0).value();
    }

}
