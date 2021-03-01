package org.vexar.titan.modules.auction.listeners;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import net.milkbowl.vault.economy.Economy;
import org.avaeriandev.api.util.BaseUtils;
import org.vexar.titan.VexarPlugin;
import org.vexar.titan.modules.auction.AuctionModule;
import org.vexar.titan.modules.auction.util.AuctionListing;
import org.vexar.titan.modules.auction.util.AuctionWard;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class AuctionSignListener implements Listener {

    private static Economy econ = Bukkit.getServer().getServicesManager().getRegistration(Economy.class).getProvider();

    private AuctionModule auctionModule;
    public AuctionSignListener() {
        this.auctionModule = VexarPlugin.getModule(AuctionModule.class);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onSignClick(PlayerInteractEvent e) {

        Player plr = e.getPlayer();

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK
                && e.getClickedBlock().getType() == Material.WALL_SIGN) {

            for(AuctionListing listing : auctionModule.getListings()) {
                if(listing.getSignBlock().equals(e.getClickedBlock())) {

                    if(listing.getOwner() == null) {
                        if(econ.getBalance(plr) >= listing.getPrice()) {

                            // Purchase listing
                            econ.withdrawPlayer(plr, listing.getPrice());
                            listing.purchase(plr);

                            // Update sign
                            Sign sign = (Sign) listing.getSignBlock().getState();
                            sign.setLine(0, BaseUtils.chat("&4[BuyChest]"));
                            sign.setLine(1, "");
                            sign.setLine(2, "Owned by:");
                            sign.setLine(3, BaseUtils.chat("&c" + plr.getName()));
                            sign.update();

                            // Notify player
                            plr.sendMessage(BaseUtils.chat("&aYou successfully purchased the listing!"));

                        } else {

                            plr.sendMessage(BaseUtils.chat("&cYou don't have enough money to purchase this listing."));

                        }
                    } else if(listing.getOwner().equals(plr.getUniqueId())) {

                        Chest chest = (Chest) listing.getChestBlock().getState();

                        // Allow owner to open chest
                        RegionManager rm = WorldGuardPlugin.inst().getRegionManager(e.getPlayer().getWorld());
                        for(AuctionWard ward : AuctionWard.values()) {
                            if(rm.getRegion(ward.getRegionName()).contains(chest.getX(), chest.getY(), chest.getZ())) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + e.getPlayer().getName() + " group add tempChest");
                                e.getPlayer().openInventory(chest.getInventory());
                            }
                        }

                    } else {
                        // Notify player that the chest isn't theirs
                        plr.sendMessage(BaseUtils.chat("&cThis isn't your chest!"));
                    }

                    break;
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player plr = (Player) e.getPlayer();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + plr.getName() + " group remove tempChest");
    }

}
