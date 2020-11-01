package org.avaeriandev.titancore.quest;

import org.avaeriandev.titancore.quest.requirements.Requirement;
import org.avaeriandev.titancore.quest.rewards.Reward;

import java.util.List;
import java.util.logging.LogRecord;

public class Quest {

    private String name;
    private List<String> lore;
    private List<Requirement> requirements;
    private List<Reward> rewards;

    protected Quest(String name, List<String> lore, List<Requirement> requirements, List<Reward> rewards) {
        this.name = name;
        this.lore = lore;
        this.requirements = requirements;
        this.rewards = rewards;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public List<Reward> getRewards() {
        return rewards;
    }
}
