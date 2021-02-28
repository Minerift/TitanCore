package org.avaeriandev.titancore.modules.quests.util.rewards;

import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.TitanPlayerAPI;
import org.bukkit.entity.Player;

public class GemReward extends Reward {

    private int gems;

    public GemReward(int gems) {
        this.gems = gems;
    }

    @Override
    public void rewardPlayer(Player plr) {
        TitanPlayer titanPlayer = TitanPlayerAPI.get(plr);

        titanPlayer.setTickets(titanPlayer.getTickets() + gems);
    }

    @Override
    public String getRewardLore() {
        return "&8+ &a&l" + gems + " gems";
    }
}
