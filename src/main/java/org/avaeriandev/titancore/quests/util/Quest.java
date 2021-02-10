package org.avaeriandev.titancore.quests.util;

import org.avaeriandev.titancore.quests.util.requirements.Requirement;
import org.avaeriandev.titancore.quests.util.rewards.Reward;

import java.util.List;

public class Quest {

    private String name;
    private List<String> lore;
    private List<? extends Requirement> requirements;
    private List<? extends Reward> rewards;
    private int ticketRedoCost;

    public Quest(String name, List<String> lore, List<? extends Requirement> requirements, List<? extends Reward> rewards, int ticketRedoCost) {
        this.name = name;
        this.lore = lore;
        this.requirements = requirements;
        this.rewards = rewards;
        this.ticketRedoCost = ticketRedoCost;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public List<? extends Requirement> getRequirements() {
        return requirements;
    }

    public List<? extends Reward> getRewards() {
        return rewards;
    }

    public int getTicketRedoCost() {
        return ticketRedoCost;
    }

}
