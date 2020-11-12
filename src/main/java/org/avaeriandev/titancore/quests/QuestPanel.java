package org.avaeriandev.titancore.quests;

import org.avaeriandev.api.panel.IconScript;
import org.avaeriandev.api.panel.Panel;
import org.avaeriandev.api.panel.PanelIcon;
import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.quest.Quest;
import org.avaeriandev.titancore.quest.QuestData;
import org.avaeriandev.titancore.quest.QuestStateEnum;
import org.avaeriandev.titancore.quest.requirements.ItemRequirement;
import org.avaeriandev.titancore.quest.requirements.Requirement;
import org.avaeriandev.titancore.quest.rewards.MoneyReward;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class QuestPanel extends Panel {

    private static final int rows = 1;

    private TitanPlayer titanPlayer;
    private QuestData questData;

    public QuestPanel(TitanPlayer titanPlayer, QuestData questData) {
        super(questData.getQuestEnum().getQuest().getName(), rows);
        this.titanPlayer = titanPlayer;
        this.questData = questData;
        setupLayout();
    }

    private void setupLayout() {

        Quest quest = questData.getQuestEnum().getQuest();
        QuestStateEnum questStateEnum = questData.getQuestState();

        Map<Integer, PanelIcon> layout = new HashMap<>();

        int redoCost = quest.getTicketRedoCost();
        IconScript activateQuestScript = new IconScript() {
            @Override
            public void run(Player plr) {

                // Verify whether quest is on cooldown
                if(questStateEnum == QuestStateEnum.COMPLETED_ON_COOLDOWN) {

                    // Verify if player has enough tickets
                    if(titanPlayer.getTickets() >= redoCost) {
                        titanPlayer.setTickets(titanPlayer.getTickets() - redoCost);
                    } else {
                        plr.sendMessage(BaseUtils.chat("&cYou don't have enough tickets!"));
                        plr.closeInventory();
                        return;
                    }
                }

                // Prepare state expire timestamp
                Calendar newExpireTimestamp = Calendar.getInstance();
                newExpireTimestamp.setTime(new Date());
                newExpireTimestamp.add(Calendar.MINUTE, 20);

                // Update quest state
                questData.setQuestStateEnum(QuestStateEnum.ACTIVE);
                questData.setStateExpireTimestamp(newExpireTimestamp.getTimeInMillis());

                // Save quest state as it is now active
                titanPlayer.getQuestDataMap().put(questData.getQuestEnum(), questData);

                // Notify player
                // TODO: REMOVE HARDCODED CODE
                for(Requirement requirement : quest.getRequirements()) {
                    ItemRequirement itemRequirement = (ItemRequirement) requirement;

                    String item = itemRequirement.getItem().getType().name().toLowerCase().replaceAll("_", " ");
                    plr.sendMessage(BaseUtils.chat("&2Bring me " + itemRequirement.getAmount() + " " + item + "."));
                }

                // Close panel
                plr.closeInventory();
            }
        };

        List<String> confirmBtnLore = new ArrayList<>();
        if(questStateEnum == QuestStateEnum.COMPLETED_ON_COOLDOWN) confirmBtnLore.add("&cDoing this quest again will cost: " + redoCost + " tickets.");
        layout.put(1, new PanelIcon("&aConfirm", confirmBtnLore, new ItemStack(Material.WOOL, 1, (byte) 5), activateQuestScript));

        // TODO: REMOVE HARDCODED LORE
        List<String> questInfoLore = new ArrayList<>();
        questInfoLore.add("&6Reward: " + ((MoneyReward) quest.getRewards().get(0)).getMoney());
        questInfoLore.add("&3Stage 1");
        for(Requirement requirement : quest.getRequirements()) {
            ItemRequirement itemRequirement = (ItemRequirement) requirement;

            String item = itemRequirement.getItem().getType().name().toLowerCase().replaceAll("_", " ");
            questInfoLore.add("&2Bring me " + itemRequirement.getAmount() + " " + item + ".");
        }

        questInfoLore.addAll(quest.getLore());

        layout.put(5, new PanelIcon("&5Task", questInfoLore, new ItemStack(Material.PAPER), null));
        layout.put(9, new PanelIcon("&cCancel", new ItemStack(Material.WOOL, 1, (byte) 14), new IconScript() {
            @Override
            public void run(Player plr) {
                plr.closeInventory();
            }
        }));

        super.loadLayout(layout);
    }

}
