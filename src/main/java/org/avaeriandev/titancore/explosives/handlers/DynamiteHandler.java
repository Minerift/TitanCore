package org.avaeriandev.titancore.explosives.handlers;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import me.clip.ezblocks.EZBlocks;
import org.avaeriandev.titancore.MagnetHandler;
import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.TitanPlayerAPI;
import org.avaeriandev.titancore.explosives.ExplosiveHandler;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DynamiteHandler extends ExplosiveHandler {

    @Override
    public void handleExplosion(@NotNull EntityExplodeEvent e, Location dynamiteLocation, Player plr) {

        World world = plr.getWorld();
        RegionManager rm = WorldGuardPlugin.inst().getRegionManager(world);
        List<Block> blocks = e.blockList();

        TitanPlayer titanPlayer = TitanPlayerAPI.get(plr);
        MagnetHandler magnetHandler = new MagnetHandler(titanPlayer) {
            @Override
            protected void useDefaultHandler(Block block, List<ItemStack> customDrops, boolean countForGems) {

                block.breakNaturally();

                if(countForGems) {
                    EZBlocks.getEZBlocks().getBreakHandler().handleBlockBreakEvent(plr, block);
                }
            }
        };

        for(Block block : blocks) {

            // Test for WorldGuard
            ApplicableRegionSet set = rm.getApplicableRegions(block.getLocation());
            if(set.allows(DefaultFlag.BLOCK_BREAK) && set.allows(DefaultFlag.OTHER_EXPLOSION)) {

                // Handle magnet
                magnetHandler.handleMagnet(block, true);

            }
        }

        // Play particle/sound animation
        world.playSound(dynamiteLocation.add(0.1, 0.1, 0.1), Sound.EXPLODE, 1F, 2F);
        world.spigot().playEffect(dynamiteLocation, Effect.EXPLOSION, 0, 0, 0, 0, 0, 1, 20, 30);

        // Cancel event
        e.setCancelled(true);
    }
}
