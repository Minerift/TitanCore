package org.avaeriandev.titancore.quest.requirements;

import org.bukkit.entity.Player;

public abstract class Requirement {

    public abstract boolean meetsRequirement(Player plr);
    public abstract void removeRequirement(Player plr);

}
