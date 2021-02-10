package org.avaeriandev.api.util;

import net.lightshard.prisoncells.PrisonCells;
import net.lightshard.prisoncells.cell.PrisonCell;
import org.bukkit.Location;

public class WorldGuardUtils {

    private WorldGuardUtils() {}

    public static boolean isInCell(Location location) {

        PrisonCell cell = PrisonCells.getInstance().getCellManager().getByLocation(location);
        return cell != null;

    }

}
