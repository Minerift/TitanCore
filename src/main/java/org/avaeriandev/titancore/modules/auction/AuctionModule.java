package org.avaeriandev.titancore.modules.auction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.avaeriandev.titancore.TitanPlugin;
import org.avaeriandev.titancore.modules.Module;
import org.avaeriandev.titancore.modules.auction.listeners.AuctionSignListener;
import org.avaeriandev.titancore.modules.auction.listeners.AuctionCellListener;
import org.avaeriandev.titancore.modules.auction.util.AuctionListing;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuctionModule implements Module {

    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(SerializationFeature.INDENT_OUTPUT, true);
    private List<AuctionListing> listings = new ArrayList<>();

    // Listeners
    private AuctionCellListener cellListener;
    private AuctionSignListener signListener;

    @Override
    public void start() {
        // Read from file
        try {
            File auctionFile = getFileFromFormat();
            this.listings = auctionFile.length() != 0 ? mapper.readValue(auctionFile, mapper.getTypeFactory().constructCollectionType(List.class, AuctionListing.class)) : new ArrayList<>();
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Register listeners
        this.cellListener = new AuctionCellListener();
        this.signListener = new AuctionSignListener();

        Bukkit.getPluginManager().registerEvents(cellListener, TitanPlugin.getInstance());
        Bukkit.getPluginManager().registerEvents(signListener, TitanPlugin.getInstance());
    }

    @Override
    public void terminate() {
        // Write to file
        try {
            File auctionFile = getFileFromFormat();
            mapper.writeValue(auctionFile, listings);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // Delete auction chests
        for(AuctionListing listing : listings) {
            if (listing.isOnDeletionTimer()) {
                listing.getDeletionTimer().cancel();
                listing.delete();
            }
        }

        // Unregister listeners
        HandlerList.unregisterAll(cellListener);
        HandlerList.unregisterAll(signListener);
    }

    public List<AuctionListing> getListings() {
        return listings;
    }

    private File getFileFromFormat() {
        File file = new File(TitanPlugin.getInstance().getDataFolder(), "auction.json");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file;
    }
}
