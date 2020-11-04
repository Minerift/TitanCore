package org.avaeriandev.titancore.auction;

import org.avaeriandev.titancore.TitanPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AuctionListing {

    private Block chest;
    private Block sign;

    private int price;
    private OfflinePlayer owner;

    private transient BukkitTask deletionTimer;
    private transient boolean isOnDeletionTimer;

    public AuctionListing(Block chest, Block sign, int price) {
        this.chest = chest;
        this.sign = sign;

        this.price = price;
        this.owner = null;

        this.deletionTimer = null;
        this.isOnDeletionTimer = false;
    }

    public AuctionListing(Map<String, Object> mappedListing) {
        this.chest = Location.deserialize((Map<String, Object>) mappedListing.get("chest")).getBlock();
        this.sign = Location.deserialize((Map<String, Object>) mappedListing.get("sign")).getBlock();
        this.price = (int) mappedListing.get("price");
    }

    public Block getChest() {
        return chest;
    }

    public Block getSign() {
        return sign;
    }

    public int getPrice() {
        return price;
    }

    public OfflinePlayer getOwner() {
        return owner;
    }

    public BukkitTask getDeletionTimer() {
        return deletionTimer;
    }

    public boolean isOnDeletionTimer() {
        return isOnDeletionTimer;
    }

    public Map<String, Object> serialize() {
        Map<String, Object> serialMap = new HashMap<>();

        serialMap.put("chest", chest.getLocation().serialize());
        serialMap.put("sign", sign.getLocation().serialize());
        serialMap.put("price", price);

        return serialMap;
    }

    public void purchase(OfflinePlayer buyer) {
        this.owner = buyer;
        this.isOnDeletionTimer = true;
        this.deletionTimer = new BukkitRunnable() {
            @Override
            public void run() {
                delete();
            }
        }.runTaskLater(TitanPlugin.getInstance(), 5 * 20); //TimeUnit.MINUTES.toSeconds(2)
    }

    public void delete() {
        Chest chestState = (Chest) chest.getState();
        chestState.getInventory().clear();

        sign.setType(Material.AIR);
        chest.setType(Material.AIR);

        AuctionSystem.listings.remove(this);
    }
}
