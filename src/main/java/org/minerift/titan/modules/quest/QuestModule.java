package org.minerift.titan.modules.quest;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.minerift.titan.TitanPlugin;
import org.minerift.titan.TitanProfile;
import org.minerift.titan.modules.Module;
import org.minerift.titan.modules.quest.commands.QuestCommand;
import org.minerift.titan.modules.quest.system.QuestData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QuestModule extends Module {

    private BukkitTask questStateCheckTimer;

    @Override
    protected void onEnable() {

        registerCommand("quest", new QuestCommand());

        // Start quest expiration check
        this.questStateCheckTimer = new BukkitRunnable() {
            @Override
            public void run() {
                System.out.println("Updating quest states...");
                updateExpiredQuests();
                System.out.println("Quest states updated.");
            }
        }.runTaskTimer(TitanPlugin.getInstance(), 0, 20 * TimeUnit.SECONDS.toSeconds(30));

    }

    @Override
    protected void onDisable() {

        questStateCheckTimer.cancel();
        questStateCheckTimer = null;

    }

    private void updateExpiredQuests() {
        for(TitanProfile profile : TitanProfile.list()) {

            List<QuestData> allData = new ArrayList<>(profile.getQuestDataMap().values());
            for(QuestData questData : allData) {

                // Check if expired
                if(System.currentTimeMillis() >= questData.getStateExpireTimestamp()) {
                    // Set as inactive
                    profile.getQuestDataMap().remove(questData.getQuestEnum());
                }
            }
        }
    }
}
