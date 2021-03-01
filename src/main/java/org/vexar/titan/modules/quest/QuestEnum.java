package org.vexar.titan.modules.quest;

import org.vexar.titan.modules.quest.system.Quest;
import org.vexar.titan.modules.quest.system.requirements.ItemRequirement;
import org.vexar.titan.modules.quest.system.requirements.SkullRequirement;
import org.vexar.titan.modules.quest.system.rewards.GemReward;
import org.vexar.titan.modules.quest.system.rewards.MoneyReward;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public enum QuestEnum {

    // TODO: E Ward
    E_WOOD(new Quest("Tim Ber Land",
            Arrays.asList("Let me axe you a question.", "Can you get me an axe?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.IRON_AXE), 1)),
            Arrays.asList(new MoneyReward(1000)),
            20
    )),
    E_MINE(new Quest("Black Lung",
            Arrays.asList("Hey friend,", "I need an iron pickaxe for a top secret project!", "Do me a solid and grab me one?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.IRON_PICKAXE), 1)),
            Arrays.asList(new MoneyReward(1000)),
            20
    )),
    E_LEATHER(new Quest("David Leatherman",
            Arrays.asList("Jay-walking Leno took my chestplate.", "Can you get me another?"),
            Arrays.asList(
                    new ItemRequirement(new ItemStack(Material.LEATHER_HELMET), 1),
                    new ItemRequirement(new ItemStack(Material.LEATHER_CHESTPLATE), 1),
                    new ItemRequirement(new ItemStack(Material.LEATHER_LEGGINGS), 1),
                    new ItemRequirement(new ItemStack(Material.LEATHER_BOOTS), 1)
            ),
            Arrays.asList(new MoneyReward(1500)),
            20
    )),
    E_VOODOO(new Quest("Voodoo",
            Arrays.asList("The guards took my last one,", "can you get me another head?"),
            Arrays.asList(new SkullRequirement(1)),
            Arrays.asList(new MoneyReward(2500)),
            20
    )),

    // TODO: D Ward
    D_CONDUCTOR(new Quest("Conductor",
            Arrays.asList("I need some help to finish my track.", "Can you get me some rails?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.RAILS), 32)),
            Arrays.asList(new MoneyReward(5000)),
            100
    )),
    D_MIA_STONER(new Quest("Mia Stoner",
            Arrays.asList("Bring me a stack of dem leeaaaves yo..."),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.LEAVES), 64)),
            Arrays.asList(new MoneyReward(4000)),
            100
    )),
    D_STANKY_JAMES(new Quest("Stanky James",
            Arrays.asList("Pert is hungry.", "Can you get him some fish?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.RAW_FISH), 20)),
            Arrays.asList(new MoneyReward(7500)),
            100
    )),

    // TODO: C Ward
    C_COOK_CHEF(new Quest("Chef",
            Arrays.asList("We are going to make you an omelette!", "Bring me 20 steak"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.RAW_BEEF), 20)),
            Arrays.asList(new MoneyReward(11250)),
            200
    )),
    C_COOK_BOY(new Quest("Boy",
            Arrays.asList("We are going to make you an omelette!", "Bring me 20 pork"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.PORK), 20)),
            Arrays.asList(new MoneyReward(11250)),
            200
    )),
    C_COOK_ARE(new Quest("Are",
            Arrays.asList("We are going to make you an omelette!", "Bring me 1 milk bucket"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.MILK_BUCKET), 1)),
            Arrays.asList(new MoneyReward(11250)),
            200
    )),
    C_COOK_DEE(new Quest("Dee",
            Arrays.asList("We are going to make you an omelette!", "Bring me 10 eggs"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.EGG), 10)),
            Arrays.asList(new MoneyReward(11250)),
            200
    )),
    C_WILE_E(new Quest("Wile E",
            Arrays.asList("Dude I got a wicked headache...", "can you get me an anvil?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.ANVIL), 1)),
            Arrays.asList(new MoneyReward(20000)),
            200
    )),
    C_KURT_THROAT(new Quest("Kurt Throat",
            Arrays.asList(new String[]{}),
            Arrays.asList(new SkullRequirement(5)),
            Arrays.asList(new MoneyReward(20000)),
            200
    )),
    C_JOHNY_APPLEPOOP(new Quest("Johny ApplePoop",
            Arrays.asList("I need to make poopie and I need 5 gold apples...", "PLZ help!"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.GOLDEN_APPLE), 5)),
            Arrays.asList(new MoneyReward(20000)),
            200
    )),

    // TODO: B Ward
    B_DESIGNER_DICK(new Quest("Designer Dick",
            Arrays.asList("Enter this hole to get to the mine!", "I need some lapis blocks to make this place fine!", "I can't pay a lot, but I can offer other", "compensation..."),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.LAPIS_BLOCK), 11)),
            Arrays.asList(new MoneyReward(140000)),
            300
    )),
    B_DIRTY_DAN(new Quest("Dirty Dan",
            Arrays.asList("I've been reselling clocks to people", "but i'm running low on stock", "Can you get me some more?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.WATCH), 25)),
            Arrays.asList(new MoneyReward(140000)),
            300
    )),
    B_DJ_MINOR(new Quest("DJ Minor",
            Arrays.asList("We've got some tunes but no speakers", "Get me something to blast these tunes", "with."),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.JUKEBOX), 32)),
            Arrays.asList(new MoneyReward(140000)),
            300
    )),
    B_FRANCIS(new Quest("Francis",
            Arrays.asList("We are planning a breakout!", "We will need some disguises.", "Bring us a leather", "chest plate."),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.LEATHER_CHESTPLATE), 1)),
            Arrays.asList(new MoneyReward(60000)),
            300
    )),
    B_BENRY(new Quest("Benry",
            Arrays.asList("We are almost ready to break", "out of this joint,", "but this wood sword won't do.", "Bring us an iron sword!"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.IRON_SWORD), 1)),
            Arrays.asList(new MoneyReward(60000)),
            300
    )),
    B_GUSTOV(new Quest("Gustov",
            Arrays.asList("We are so close!", "All we need is 34 redstone", "repeaters to setup our TNT!"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.DIODE), 34)),
            Arrays.asList(new MoneyReward(60000)),
            300
    )),

    // TODO: A Ward
    A_MINER(new Quest("Miner",
            Arrays.asList("Hey you! I'm trying to get out", "of this stinking prison and I need", "your help! Can you", "get me 1 Diamond pick", "10 Rails", "and 1 Minecart?"),
            Arrays.asList(
                    new ItemRequirement(new ItemStack(Material.DIAMOND_PICKAXE), 1),
                    new ItemRequirement(new ItemStack(Material.RAILS), 10),
                    new ItemRequirement(new ItemStack(Material.MINECART), 1)
            ),
            Arrays.asList(new MoneyReward(300000)),
            400
    )),
    A_DOOD(new Quest("Dood",
            Arrays.asList("Hey Dood,", "I need 30 emerald ORE to buy my", "friend's auction house chest.", "Can you help me out?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.EMERALD_ORE), 30)),
            Arrays.asList(new MoneyReward(300000)),
            400
    )),

    TITAN_RADIANT_RYAN(new Quest("Radiant Ryan",
            Arrays.asList("Ayo! I'm hauling this glowstone over", "and I need your help.", "Mind giving me a hand?"),
            Arrays.asList(new ItemRequirement(new ItemStack(Material.GLOWSTONE), 18)),
            Arrays.asList(new MoneyReward(400000), new GemReward(500)),
            1000
    ));

    private Quest quest;
    QuestEnum(Quest quest) {
        this.quest = quest;
    }

    public Quest getQuest() {
        return quest;
    }

}
