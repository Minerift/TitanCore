package org.minerift.titan.modules.gens;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import net.milkbowl.vault.economy.Economy;
import org.avaeriandev.api.util.VectorSerializable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.minerift.titan.TitanPlugin;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Generator {

    @JsonIgnore
    private static Economy econ = Bukkit.getServer().getServicesManager().getRegistration(Economy.class).getProvider();

    @JsonIgnore
    private final GeneratorModule MODULE = TitanPlugin.getModule(GeneratorModule.class);

    private int level = 1;
    private int maxStorage = 500000;
    private long claimTimestamp = System.currentTimeMillis();

    private VectorSerializable location;

    public Generator() {}

    @JsonIgnore
    public UUID getOwnerUUID() {
        for(Map.Entry<UUID, List<Generator>> entry : MODULE.getGeneratorMap().entrySet()) {
            if(entry.getValue().contains(this)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @JsonIgnore
    public OfflinePlayer getOwner() {
        return Bukkit.getOfflinePlayer(getOwnerUUID());
    }

    @JsonIgnore
    public int getMoneyGenerated() {
        long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - claimTimestamp);
        int timesPaid = (int) (seconds / MODULE.getPayFrequency());
        return getRate() * timesPaid;
    }

    @JsonIgnore
    public int getUpgradeCost() {
        return level * 100;
    }

    @JsonIgnore
    public int getRate() {
        return level * 100; // lvl 1 = 100, lvl 2 = 200, lvl 3 = 300, etc.
    }

    @JsonIgnore
    public Block getBlock() {
        World world = Bukkit.getWorld("Titan");
        return location.toBukkitVector().toLocation(world).getBlock();
    }

    public void claimMoney() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + getOwner().getName() + " " + getMoneyGenerated());
        claimTimestamp = System.currentTimeMillis();
    }

    public void upgrade() {
        level++;
    }

    public boolean canAffordUpgrade() {
        return econ.getBalance(getOwner()) >= getUpgradeCost();
    }

}
