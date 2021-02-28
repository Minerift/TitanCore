package org.avaeriandev.titancore.modules.explosives;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.jetbrains.annotations.NotNull;

public abstract class ExplosiveHandler {

    public abstract void handleExplosion(@NotNull EntityExplodeEvent e, Location dynamiteLocation, Player plr);

}
