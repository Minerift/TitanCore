package org.vexar.titan.modules.auction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.vexar.titan.VexarPlugin;
import org.vexar.titan.modules.Module;
import org.vexar.titan.modules.auction.listeners.AuctionSignListener;
import org.vexar.titan.modules.auction.listeners.AuctionCellListener;
import org.vexar.titan.modules.auction.util.AuctionListing;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuctionModule extends Module {

    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(SerializationFeature.INDENT_OUTPUT, true);
    private List<AuctionListing> listings = new ArrayList<>();

    @Override
    protected void onEnable() {
        // Read from file
        try {
            File auctionFile = getFileFromFormat();
            this.listings = auctionFile.length() != 0 ? mapper.readValue(auctionFile, mapper.getTypeFactory().constructCollectionType(List.class, AuctionListing.class)) : new ArrayList<>();
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Register listeners
        registerListener(new AuctionCellListener());
        registerListener(new AuctionSignListener());
    }

    @Override
    protected void onDisable() {
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
    }

    public List<AuctionListing> getListings() {
        return listings;
    }

    private File getFileFromFormat() {
        File file = new File(VexarPlugin.getInstance().getDataFolder(), "auction.json");
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
