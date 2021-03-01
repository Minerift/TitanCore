package org.vexar.titan.modules.quest.commands;

import org.avaeriandev.api.util.BaseUtils;
import org.vexar.titan.VexarProfile;
import org.vexar.titan.modules.quest.QuestEnum;
import org.vexar.titan.modules.quest.system.QuestData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QuestCommand implements CommandExecutor {

    // Format: /quest <identifier> <username>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!sender.hasPermission("minerift.admin")) {
            sender.sendMessage(BaseUtils.chat("&cYou have no permission!"));
            return false;
        }

        if(args.length < 2) {
            sender.sendMessage(BaseUtils.chat("&cIncorrect arguments."));
            return false;
        }

        Player plr = Bukkit.getPlayer(args[1]);
        VexarProfile vexarProfile = VexarProfile.get(plr);

        QuestEnum questEnum = QuestEnum.valueOf(args[0].toUpperCase());
        QuestData questData = vexarProfile.getDataForQuest(questEnum);

        questData.getQuestState().getHandler().getResponse(vexarProfile, questData);

        return true;
    }
}