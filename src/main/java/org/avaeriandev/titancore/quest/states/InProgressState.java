package org.avaeriandev.titancore.quest.states;

import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.quest.Quest;
import org.avaeriandev.titancore.quest.QuestData;
import org.avaeriandev.titancore.quest.QuestStateEnum;
import org.avaeriandev.titancore.quest.requirements.ItemRequirement;
import org.avaeriandev.titancore.quest.requirements.Requirement;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.Date;

public class InProgressState extends State {

    @Override
    public void getResponse(TitanPlayer titanPlayer, QuestData questData) {

        // General
        Player plr = titanPlayer.getPlayer().getPlayer();
        Quest quest = questData.getQuestEnum().getQuest();

        // Verify whether player has correct requirements
        for(Requirement requirement : quest.getRequirements()) {
            if(!requirement.meetsRequirement(plr)) {

                // Print requirements for player again
                // TODO: REMOVE HARDCODED CODE
                for(Requirement req : quest.getRequirements()) {
                    ItemRequirement itemRequirement = (ItemRequirement) req;

                    String item = itemRequirement.getItem().getType().name().toLowerCase().replaceAll("_", " ");
                    plr.sendMessage(BaseUtils.chat("&2Bring me " + itemRequirement.getAmount() + " " + item + "."));
                }

                return;
            }
        }

        // Player meets requirements; take requirements and reward player
        quest.getRequirements().forEach(requirement -> requirement.removeRequirement(plr));
        quest.getRewards().forEach(reward -> reward.rewardPlayer(plr));

        // Notify player
        plr.sendMessage(BaseUtils.chat("&aYou completed the quest!"));

        // Prepare next state expiration timestamp
        Calendar newExpireTimestamp = Calendar.getInstance();
        newExpireTimestamp.setTime(new Date());
        newExpireTimestamp.add(Calendar.HOUR_OF_DAY, 24);

        // Update quest state
        questData.setQuestStateEnum(QuestStateEnum.COMPLETED_ON_COOLDOWN);
        questData.setStateExpireTimestamp(newExpireTimestamp.getTimeInMillis());

    }
}