package org.avaeriandev.titancore.quest.states;

import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.quest.QuestData;
import org.avaeriandev.titancore.quests.QuestPanel;
import org.bukkit.entity.Player;

public class CompletedState extends State {

    @Override
    public void getResponse(TitanPlayer titanPlayer, QuestData questData) {

        Player plr = titanPlayer.getPlayer().getPlayer();

        plr.openInventory(new QuestPanel(titanPlayer, questData).getPanel());
    }
}