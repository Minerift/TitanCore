package org.avaeriandev.titancore.quests;

import org.avaeriandev.titancore.quest.Quest;
import org.avaeriandev.titancore.quest.requirements.ItemRequirement;
import org.avaeriandev.titancore.quest.requirements.Requirement;
import org.avaeriandev.titancore.quest.rewards.MoneyReward;
import org.avaeriandev.titancore.quest.rewards.Reward;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public enum QuestType {

    E_WOOD(1, new Quest("Tim Ber Land",
            Arrays.asList("Let me axe you a question.", "Can you get me an axe?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.IRON_AXE, 1))),
            Arrays.asList(new MoneyReward(1000)))),

    E_MINE(2, new Quest("Black Lung",
            Arrays.asList("Hey friend,", "I need an iron pickaxe for a top secret project!", "Do me a solid and grab me one?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.IRON_PICKAXE, 1))),
            Arrays.asList(new MoneyReward(1000)))),

    E_LEATHER(3, new Quest("David Leatherman",
            Arrays.asList("Jay-walking Leno took my chestplate.", "Can you get me another?"),
            Arrays.asList(
                    new ItemRequirement(new ItemStack(Material.LEATHER_HELMET, 1)),
                    new ItemRequirement(new ItemStack(Material.LEATHER_CHESTPLATE, 1)),
                    new ItemRequirement(new ItemStack(Material.LEATHER_LEGGINGS, 1)),
                    new ItemRequirement(new ItemStack(Material.LEATHER_BOOTS, 1))
            ),
            Arrays.asList(new MoneyReward(1500)))),

    E_VOODOO(4, new Quest("Voodoo",
            Arrays.asList("The guards took my last one,", "can you get me another head?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3))),
            Arrays.asList(new MoneyReward(2500)))),

    ;

    private int identifier;
    private Quest quest;
    private QuestType(int identifier, Quest quest) {
        this.identifier = identifier;
        this.quest = quest;
    }

    public int getId() {
        return identifier;
    }

    public Quest getQuest() {
        return quest;
    }

}
