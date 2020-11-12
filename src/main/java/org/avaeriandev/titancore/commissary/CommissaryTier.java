package org.avaeriandev.titancore.commissary;

import org.avaeriandev.api.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum CommissaryTier {
    C1(150,
        new Location(Bukkit.getWorld("Titan"), 242.5, 48, 1298.5, -180, 0),
        new Location(Bukkit.getWorld("Titan"), 242, 53, 1300),
        new ItemBuilder(new ItemStack(Material.PAPER, 1)).setName("&bC1 Ticket").create()
    ),
    C2(300,
        new Location(Bukkit.getWorld("Titan"), 242.5, 48, 1270.5, -180, 0),
        new Location(Bukkit.getWorld("Titan"), 242, 53, 1272),
        new ItemBuilder(new ItemStack(Material.PAPER, 1)).setName("&aC2 Ticket").create()
    ),
    C3(500,
        new Location(Bukkit.getWorld("Titan"), 242.5, 48, 1242.5, -180, 0),
        new Location(Bukkit.getWorld("Titan"), 242, 53, 1244),
        new ItemBuilder(new ItemStack(Material.PAPER, 1)).setName("&dC3 Ticket").create()
    );

    private int ticketPrice;
    private Location interiorLocation;
    private Location signLocation;
    private ItemStack ticketItem;
    CommissaryTier(int ticketPrice, Location interiorLocation, Location signLocation, ItemStack ticketItem) {
        this.ticketPrice = ticketPrice;
        this.interiorLocation = interiorLocation;
        this.signLocation = signLocation;
        this.ticketItem = ticketItem;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public Location getInteriorLocation() {
        return interiorLocation;
    }

    public Location getSignLocation() {
        return signLocation;
    }

    public ItemStack getTicketItem() {
        return ticketItem;
    }
}
