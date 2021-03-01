package org.vexar.titan.modules.quest.system.states;

import org.vexar.titan.VexarProfile;
import org.vexar.titan.modules.quest.system.QuestData;
import org.vexar.titan.modules.quest.panels.QuestPanel;
import org.bukkit.entity.Player;

public class InactiveState extends State {

    @Override
    public void getResponse(VexarProfile vexarProfile, QuestData questData) {

        Player plr = vexarProfile.getPlayer().getPlayer();

        plr.openInventory(new QuestPanel(vexarProfile, questData).getPanel());
    }
}