package org.avaeriandev.titancore.quests.util.states;

import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.quests.util.QuestData;
import org.avaeriandev.titancore.quests.QuestPanel;
import org.bukkit.entity.Player;

public class InactiveState extends State {

    @Override
    public void getResponse(TitanPlayer titanPlayer, QuestData questData) {

        Player plr = titanPlayer.getPlayer().getPlayer();

        plr.openInventory(new QuestPanel(titanPlayer, questData).getPanel());
    }
}