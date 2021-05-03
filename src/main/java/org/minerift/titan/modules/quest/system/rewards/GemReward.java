package org.minerift.titan.modules.quest.system.rewards;

import org.minerift.titan.TitanProfile;
import org.bukkit.entity.Player;

public class GemReward extends Reward {

    private int gems;

    public GemReward(int gems) {
        this.gems = gems;
    }

    @Override
    public void rewardPlayer(Player plr) {
        TitanProfile titanProfile = TitanProfile.get(plr);

        titanProfile.setTickets(titanProfile.getTickets() + gems);
    }

    @Override
    public String getRewardLore() {
        return "&8+ &a&l" + gems + " gems";
    }
}
