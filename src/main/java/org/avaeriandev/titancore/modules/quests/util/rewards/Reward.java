package org.avaeriandev.titancore.modules.quests.util.rewards;

import org.bukkit.entity.Player;

public abstract class Reward {

    public abstract void rewardPlayer(Player plr);
    public abstract String getRewardLore();

}
