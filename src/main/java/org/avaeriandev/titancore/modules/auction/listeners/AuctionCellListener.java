package org.avaeriandev.titancore.modules.auction.listeners;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.lightshard.prisoncells.event.CellUnclaimEvent;
import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.api.util.ByteDirection;
import org.avaeriandev.titancore.TitanPlugin;
import org.avaeriandev.titancore.modules.auction.AuctionModule;
import org.avaeriandev.titancore.modules.auction.util.AuctionListing;
import org.avaeriandev.titancore.modules.auction.util.AuctionSection;
import org.avaeriandev.titancore.modules.auction.util.AuctionWard;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AuctionCellListener implements Listener {

    private static final int MAX_LOCATION_ATTEMPTS = 10;

    private AuctionModule auctionModule;
    public AuctionCellListener() {
        this.auctionModule = TitanPlugin.getModule(AuctionModule.class);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onCellUnrent(CellUnclaimEvent e) {

        // Retrieve and parse username of cell owner
        String username = e.getPreviousOwner().getName();
        username = username.substring(username.indexOf(',') + 1);

        // Retrieve and parse cell ward
        String cellWard = e.getCell().getName().replaceAll("\\d", "");
        AuctionWard auctionWard;
        try {
            auctionWard = AuctionWard.valueOf(cellWard);
        } catch (IllegalArgumentException ex) {
            return;
        }

        // Retrieve region
        World world = e.getCell().getWorld();

        RegionManager rm = WorldGuardPlugin.inst().getRegionManager(world);
        ProtectedRegion region = rm.getRegion(e.getCell().getName() + "mainregion");

        BlockVector minPoint = region.getMinimumPoint();
        BlockVector maxPoint = region.getMaximumPoint();

        // Scan through region and store chest contents
        List<ItemStack> chestContents = new ArrayList<>();
        List<Chest> duplicateChests = new ArrayList<>();

        Location selectedLoc;

        for(double x = minPoint.getX(); x < maxPoint.getX() + 1; x++) {
            for(double y = minPoint.getY(); y < maxPoint.getY() + 1; y++) {
                for(double z = minPoint.getZ(); z < maxPoint.getZ() + 1; z++) {

                    // Determine whether block at location is a chest type
                    selectedLoc = new Location(world, x,y,z);
                    Material blockType = selectedLoc.getBlock().getType();
                    if(blockType == Material.CHEST || blockType == Material.TRAPPED_CHEST) {

                        // Prepare to store contents into chestContents list
                        Chest chest = (Chest) selectedLoc.getBlock().getState();
                        InventoryHolder holder = chest.getInventory().getHolder();

                        // Determine whether chest has contents that need to be added to list
                        if(chest.getInventory().getContents() != null) {

                            // Handle double chests properly and prevent duplication
                            if(holder instanceof DoubleChest) {
                                DoubleChest doubleChest = (DoubleChest) holder;
                                Chest leftChest = (Chest) doubleChest.getLeftSide();
                                Chest rightChest = (Chest) doubleChest.getRightSide();

                                if(!(duplicateChests.contains(leftChest) || duplicateChests.contains(rightChest))) {
                                    duplicateChests.add(chest == leftChest ? rightChest : leftChest);
                                } else {
                                    // Chest contents have already been processed; skip
                                    continue;
                                }
                            }

                            // Add chest contents to chestContents list
                            for (ItemStack item : chest.getInventory().getContents()) {
                                if(item != null) {
                                    System.out.println(item.getType().name());
                                    chestContents.add(new ItemStack(item));
                                }
                            }
                        }
                    }
                }
            }
        }

        if(chestContents.isEmpty()) return;

        // Iterate through chests
        byte numChests = (byte) Math.ceil((float) chestContents.size() / 27F);
        System.out.println("numChests: " + numChests);
        for(byte i = 0; i < numChests; i++) {

            // Try and spawn at a location until a valid location is found
            Location location;
            AuctionSection section;
            int attempts = 1;
            while(true) {

                // Select a random position
                List<AuctionSection> allPositions = auctionWard.getPositions();

                byte randomWall = (byte) new Random().nextInt(allPositions.size());
                section = allPositions.get(randomWall);

                int randomX = section.getMaxX() - section.getMinX() != 0 ? (section.getMinX() + (new Random().nextInt((section.getMaxX() - section.getMinX()) / 2) * 2)) : section.getMinX();
                int randomZ = section.getMaxZ() - section.getMinZ() != 0 ? (section.getMinZ() + (new Random().nextInt((section.getMaxZ() - section.getMinZ()) / 2) * 2)) : section.getMinZ();
                int randomY = section.getInitialY() + (new Random().nextInt(section.getRowCount()) * section.getOffsetY());

                location = new Location(world, randomX, randomY, randomZ);
                if(location.getBlock().getType() == Material.AIR) {
                    System.out.println("Block at location " + randomX + ", " + randomY + ", " + randomZ + " is now an auction house listing!");
                    // Open location has been found! Break out of look loop and set chest
                    break;
                }
                System.out.println("Block at location " + randomX + ", " + randomY + ", " + randomZ + " is occupied! Attempting to find another location...");

                if(attempts >= 10) {
                    System.out.println("Failed to find a location after " + MAX_LOCATION_ATTEMPTS + " attempts.");
                    return;
                }
                attempts++;
            }

            // Create a chest
            Block block = location.getBlock();
            block.setType(Material.CHEST);
            BlockState state = block.getState();
            state.setData(new MaterialData(Material.CHEST, section.getChestDirection().getByteId()));
            state.update();

            // Fill chest with 27 slots of items (or until there are no more items)
            // Remove those 27 items from list for next chest
            Chest chest = (Chest) state;
            List<ItemStack> singularChestContents = new ArrayList<>(chestContents.subList(0, Math.min(27, chestContents.size())));
            for(int j = 0; j < singularChestContents.size(); j++) {
                chest.getInventory().setItem(j, singularChestContents.get(j));
                chestContents.remove(0);
            }

            // Create sign at location
            Location signLocation = getLocationInFront(section.getChestDirection(), location);
            int price = new Random().nextInt(5000);

            signLocation.getBlock().setType(Material.WALL_SIGN);
            Sign sign = (Sign) signLocation.getBlock().getState();

            sign.setRawData(chest.getRawData());
            sign.setLine(0, BaseUtils.chat("&e[BuyChest]"));
            sign.setLine(1, username);
            sign.setLine(3, String.valueOf(price));
            sign.update();

            AuctionListing listing = new AuctionListing(chest.getBlock(), sign.getBlock(), price);
            auctionModule.getListings().add(listing);

        }

    }

    private static Location getLocationInFront(ByteDirection frontFace, Location location) {
        switch(frontFace) {
            case NORTH: return location.clone().subtract(0, 0, 1);
            case SOUTH: return location.clone().add(0, 0, 1);
            case WEST: return location.clone().subtract(1, 0, 0);
            case EAST: return location.clone().add(1, 0, 0);
        }
        return null;
    }

}
