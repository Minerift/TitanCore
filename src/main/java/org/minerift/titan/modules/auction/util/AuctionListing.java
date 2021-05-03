package org.minerift.titan.modules.auction.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.avaeriandev.api.util.VectorSerializable;
import org.minerift.titan.TitanPlugin;
import org.minerift.titan.modules.auction.AuctionModule;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AuctionListing {

    private static final World AUCTION_WORLD = Bukkit.getWorld("Titan");

    private VectorSerializable chest;
    private VectorSerializable sign;

    private int price;
    private UUID owner;

    @JsonIgnore
    private BukkitTask deletionTimer;
    @JsonIgnore
    private boolean isOnDeletionTimer;

    private AuctionListing() {}

    public AuctionListing(Block chest, Block sign, int price) {
        this.chest = new VectorSerializable(chest.getLocation().toVector());
        this.sign = new VectorSerializable(sign.getLocation().toVector());

        this.price = price;
        this.owner = null;

        this.deletionTimer = null;
        this.isOnDeletionTimer = false;
    }

    @JsonIgnore
    public Block getChestBlock() {
        return chest.toBukkitVector().toLocation(AUCTION_WORLD).getBlock();
    }

    @JsonIgnore
    public Block getSignBlock() {
        return sign.toBukkitVector().toLocation(AUCTION_WORLD).getBlock();
    }

    public int getPrice() {
        return price;
    }

    public UUID getOwner() {
        return owner;
    }

    @JsonIgnore
    public BukkitTask getDeletionTimer() {
        return deletionTimer;
    }

    @JsonIgnore
    public boolean isOnDeletionTimer() {
        return isOnDeletionTimer;
    }

    public void purchase(OfflinePlayer buyer) {
        this.owner = buyer.getUniqueId();
        this.isOnDeletionTimer = true;
        this.deletionTimer = new BukkitRunnable() {
            @Override
            public void run() {
                delete();
            }
        }.runTaskLater(TitanPlugin.getInstance(), TimeUnit.MINUTES.toSeconds(2) * 20);
    }

    public void delete() {
        Chest chestState = (Chest) getChestBlock().getState();
        chestState.getInventory().clear();

        getSignBlock().setType(Material.AIR);
        getChestBlock().setType(Material.AIR);

        ((AuctionModule) TitanPlugin.getModule(AuctionModule.class)).getListings().remove(this);
    }
}
