package org.vexar.titan.modules.quest.system.states;

import org.avaeriandev.api.util.BaseUtils;
import org.vexar.titan.VexarProfile;
import org.vexar.titan.modules.quest.system.Quest;
import org.vexar.titan.modules.quest.system.QuestData;
import org.vexar.titan.modules.quest.system.QuestStateEnum;
import org.vexar.titan.modules.quest.system.requirements.Requirement;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.Date;

public class InProgressState extends State {

    @Override
    public void getResponse(VexarProfile vexarProfile, QuestData questData) {

        // General
        Player plr = vexarProfile.getPlayer().getPlayer();
        Quest quest = questData.getQuestEnum().getQuest();

        // Verify whether player has correct requirements
        for(Requirement requirement : quest.getRequirements()) {
            if(!requirement.meetsRequirement(plr)) {

                // Print requirements for player again
                quest.getRequirements().forEach(req -> req.remindPlayer(plr));
                return;
            }
        }

        // Player meets requirements; take requirements and reward player
        quest.getRequirements().forEach(requirement -> requirement.removeRequirement(plr));
        quest.getRewards().forEach(reward -> reward.rewardPlayer(plr));

        // Notify player
        plr.sendMessage(BaseUtils.chat("&aYou completed the &a&lchallenge!"));

        // Prepare next state expiration timestamp
        Calendar newExpireTimestamp = Calendar.getInstance();
        newExpireTimestamp.setTime(new Date());
        newExpireTimestamp.add(Calendar.HOUR_OF_DAY, 24);

        // Update quest state
        questData.setQuestStateEnum(QuestStateEnum.COMPLETED_ON_COOLDOWN);
        questData.setStateExpireTimestamp(newExpireTimestamp.getTimeInMillis());

    }
}