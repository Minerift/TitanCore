package org.minerift.titan.modules.quest.system.rewards;

import org.bukkit.entity.Player;

public abstract class Reward {

    public abstract void rewardPlayer(Player plr);
    public abstract String getRewardLore();

}
