package org.minerift.titan.modules.quest.system.states;

import org.minerift.titan.TitanProfile;
import org.minerift.titan.modules.quest.system.QuestData;
import org.minerift.titan.modules.quest.panels.QuestPanel;
import org.bukkit.entity.Player;

public class CompletedState extends State {

    @Override
    public void getResponse(TitanProfile titanProfile, QuestData questData) {

        Player plr = titanProfile.getPlayer().getPlayer();

        plr.openInventory(new QuestPanel(titanProfile, questData).getPanel());
    }
}