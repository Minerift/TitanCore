package org.avaeriandev.titancore.quest;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class QuestAPI {

    private static Map<Integer, Quest> registeredQuests = new HashMap<>();

    public static void registerQuest(int identifier, Quest quest) {
        if(registeredQuests.containsKey(identifier)) {
            System.err.println("Quest identifier " + identifier + " is already in use.");
            System.err.println("Quest was not registered.");
            return;
        }
    }

    public static Quest getQuestData(int identifier) {
        return registeredQuests.get(identifier);
    }

    public static void requestQuestTimeCheck() {

    }

}
