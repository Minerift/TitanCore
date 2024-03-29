package org.minerift.titan.modules.quest.panels;

import org.avaeriandev.api.panel.IconScript;
import org.avaeriandev.api.panel.Panel;
import org.avaeriandev.api.panel.PanelIcon;
import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.api.util.ItemBuilder;
import org.minerift.titan.TitanProfile;
import org.minerift.titan.modules.quest.system.QuestData;
import org.minerift.titan.modules.quest.system.QuestStateEnum;
import org.minerift.titan.modules.quest.system.Quest;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class QuestPanel extends Panel {

    private static final int rows = 1;

    private TitanProfile titanProfile;
    private QuestData questData;

    public QuestPanel(TitanProfile titanProfile, QuestData questData) {
        super(questData.getQuestEnum().getQuest().getName(), rows);
        this.titanProfile = titanProfile;
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
                    if(titanProfile.getTickets() >= redoCost) {
                        titanProfile.setTickets(titanProfile.getTickets() - redoCost);
                    } else {
                        plr.sendMessage(BaseUtils.chat("&cYou don't have enough gems!"));
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
                titanProfile.getQuestDataMap().put(questData.getQuestEnum(), questData);

                // Notify player
                plr.sendMessage(BaseUtils.chat("&aYou activated this &a&lchallenge!"));
                plr.playSound(plr.getLocation(), Sound.HORSE_ARMOR, 1, 1);
                quest.getRequirements().forEach(requirement -> requirement.remindPlayer(plr));

                // Close panel
                plr.closeInventory();
            }
        };

        List<String> confirmBtnLore = new ArrayList<>();
        if(questStateEnum == QuestStateEnum.COMPLETED_ON_COOLDOWN) confirmBtnLore.add("&cDoing this quest again will cost: " + redoCost + " gems.");
        layout.put(2, new PanelIcon("&aAccept", confirmBtnLore, new ItemStack(Material.INK_SACK, 1, (byte) 10), activateQuestScript));

        List<String> questInfoLore = new ArrayList<>();

        // Requirements
        quest.getRequirements().forEach(requirement -> questInfoLore.add(requirement.getRequirementLore()));
        questInfoLore.add("");

        // Rewards
        questInfoLore.add("&7Rewards:");
        quest.getRewards().forEach(reward -> questInfoLore.add(reward.getRewardLore()));
        questInfoLore.add("");

        // Miscellaneous Lore
        quest.getLore().forEach(line -> questInfoLore.add("&8&o" + line));
        questInfoLore.add("");
        
        questInfoLore.add("&eClick to activate this challenge.");

        layout.put(5, new PanelIcon("&6Challenge", questInfoLore, new ItemBuilder(new ItemStack(Material.MAP)).addFlag(ItemFlag.values()).create(), null));
        layout.put(8, new PanelIcon("&cCancel", new ItemStack(Material.INK_SACK, 1, (byte) 1), new IconScript() {
            @Override
            public void run(Player plr) {
                plr.closeInventory();
            }
        }));

        super.loadLayout(layout);
    }

}
