package org.avaeriandev.titancore.quests;

import org.avaeriandev.titancore.quest.Quest;
import org.avaeriandev.titancore.quest.requirements.ItemRequirement;
import org.avaeriandev.titancore.quest.rewards.MoneyReward;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public enum QuestType {

    // TODO: E Ward
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

    // TODO: D Ward
    D_CONDUCTOR(5, new Quest("Conductor",
            Arrays.asList("I need some help to finish my track.", "Can you get me some rails?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3))),
            Arrays.asList(new MoneyReward(5000)))),
    D_MIA_STONER(6, new Quest("Mia Stoner",
            Arrays.asList("Bring me a stack of dem leeaaaves yo..."),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3))),
            Arrays.asList(new MoneyReward(4000)))),
    D_STANKY_JAMES(7, new Quest("Stanky James",
            Arrays.asList("Pert is hungry.", "Can you get him some fish?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3))),
            Arrays.asList(new MoneyReward(7500)))),

    // TODO: C Ward
    C_COOK_CHEF(8, new Quest("Chef",
            Arrays.asList("We are going to make you an omelette!", "Bring me 20 steak"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.RAW_BEEF, 20))),
            Arrays.asList(new MoneyReward(11250)))),
    C_COOK_BOY(9, new Quest("Boy",
            Arrays.asList("We are going to make you an omelette!", "Bring me 20 pork"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.PORK, 20))),
            Arrays.asList(new MoneyReward(11250)))),
    C_COOK_ARE(10, new Quest("Are",
            Arrays.asList("We are going to make you an omelette!", "Bring me 1 milk bucket"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.MILK_BUCKET, 1))),
            Arrays.asList(new MoneyReward(11250)))),
    C_COOK_DEE(11, new Quest("Dee",
            Arrays.asList("We are going to make you an omelette!", "Bring me 10 eggs"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.EGG, 10))),
            Arrays.asList(new MoneyReward(11250)))),
    C_WILE_E(12, new Quest("Wile E",
            Arrays.asList("Dude I got a wicked headache...", "can you get me an anvil?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.ANVIL, 1))),
            Arrays.asList(new MoneyReward(20000)))),
    C_KURT_THROAT(13, new Quest("Kurt Throat",
            Arrays.asList(new String[]{}),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.SKULL_ITEM, 5, (byte) 3))),
            Arrays.asList(new MoneyReward(20000)))),
    C_JOHNY_APPLEPOOP(14, new Quest("Johny ApplePoop",
            Arrays.asList("I need to make poopie and I need 5 gold apples...", "PLZ help!"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.GOLDEN_APPLE, 5))),
            Arrays.asList(new MoneyReward(20000)))),

    // TODO: B Ward
    B_DESIGNER_DICK(15, new Quest("Designer Dick",
            Arrays.asList("I need to make poopie and I need 5 gold apples...", "PLZ help!"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.GOLDEN_APPLE, 5))),
            Arrays.asList(new MoneyReward(14000)))),
    B_DIRTY_DAN(16, new Quest("Johny ApplePoop",
            Arrays.asList("I need to make poopie and I need 5 gold apples...", "PLZ help!"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.GOLDEN_APPLE, 5))),
            Arrays.asList(new MoneyReward(14000)))),
    B_DJ_MINOR(17, new Quest("Johny ApplePoop",
            Arrays.asList("I need to make poopie and I need 5 gold apples...", "PLZ help!"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.GOLDEN_APPLE, 5))),
            Arrays.asList(new MoneyReward(14000)))),
    B_FRANCIS(18, new Quest("Johny ApplePoop",
            Arrays.asList("I need to make poopie and I need 5 gold apples...", "PLZ help!"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.GOLDEN_APPLE, 5))),
            Arrays.asList(new MoneyReward(60000)))),
    B_BENRY(19, new Quest("Johny ApplePoop",
            Arrays.asList("I need to make poopie and I need 5 gold apples...", "PLZ help!"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.GOLDEN_APPLE, 5))),
            Arrays.asList(new MoneyReward(60000)))),
    B_GUSTOV(20, new Quest("Johny ApplePoop",
            Arrays.asList("I need to make poopie and I need 5 gold apples...", "PLZ help!"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.GOLDEN_APPLE, 5))),
            Arrays.asList(new MoneyReward(60000)))),

    // TODO: A Ward
    A_MINER(21, new Quest("Miner",
            Arrays.asList("Hey you! I'm trying to get out", "of this stinking prison and I need", "your help! Can you", "get me 1 Diamond pick", "10 Rails", "and 1 Minecart?"),
            Arrays.asList(
                    new ItemRequirement(new ItemStack(Material.DIAMOND_PICKAXE, 1)),
                    new ItemRequirement(new ItemStack(Material.RAILS, 10)),
                    new ItemRequirement(new ItemStack(Material.MINECART, 1))
            ),
            Arrays.asList(new MoneyReward(300000)))),
    A_DOOD(22, new Quest("Dood",
            Arrays.asList("Hey Dood,", "I need 30 emerald ORE to buy my", "friend's auction house chest.", "Can you help me out?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.EMERALD_ORE, 30))),
            Arrays.asList(new MoneyReward(300000))));

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
