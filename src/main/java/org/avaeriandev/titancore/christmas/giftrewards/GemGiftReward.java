package org.avaeriandev.titancore.christmas.giftrewards;

import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.TitanPlayerAPI;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class GemGiftReward extends GiftReward {

    private int gems;

    public GemGiftReward(int gems) {
        this.gems = gems;
    }

    @Override
    public void rewardPlayer(Player plr) {
        TitanPlayer titanPlayer = TitanPlayerAPI.get(plr);
        titanPlayer.setTickets(titanPlayer.getTickets() + gems);
        plr.sendMessage(BaseUtils.chat("&aYou found " + gems + " gems from a gift!"));
        plr.playSound(plr.getLocation(), Sound.LEVEL_UP, 1, BaseUtils.randomFloat(1.5F, 2F));
    }
}
