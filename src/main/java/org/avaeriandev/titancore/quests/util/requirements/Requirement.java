package org.avaeriandev.titancore.quests.util.requirements;

import org.bukkit.entity.Player;

public abstract class Requirement {

    public abstract boolean meetsRequirement(Player plr);
    public abstract void removeRequirement(Player plr);

    // TODO
    public abstract void remindPlayer(Player plr);
    public abstract String getRequirementLore();

}
