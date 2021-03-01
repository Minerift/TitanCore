package org.vexar.titan.modules.quest.system.rewards;

import org.vexar.titan.VexarProfile;
import org.bukkit.entity.Player;

public class GemReward extends Reward {

    private int gems;

    public GemReward(int gems) {
        this.gems = gems;
    }

    @Override
    public void rewardPlayer(Player plr) {
        VexarProfile vexarProfile = VexarProfile.get(plr);

        vexarProfile.setTickets(vexarProfile.getTickets() + gems);
    }

    @Override
    public String getRewardLore() {
        return "&8+ &a&l" + gems + " gems";
    }
}
