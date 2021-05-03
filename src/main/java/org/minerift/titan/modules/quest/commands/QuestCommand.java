package org.minerift.titan.modules.quest.commands;

import org.avaeriandev.api.util.BaseUtils;
import org.minerift.titan.TitanProfile;
import org.minerift.titan.modules.quest.QuestEnum;
import org.minerift.titan.modules.quest.system.QuestData;
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
        TitanProfile titanProfile = TitanProfile.get(plr);

        QuestEnum questEnum = QuestEnum.valueOf(args[0].toUpperCase());
        QuestData questData = titanProfile.getDataForQuest(questEnum);

        questData.getQuestState().getHandler().getResponse(titanProfile, questData);

        return true;
    }
}