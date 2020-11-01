package org.avaeriandev.titancore.quest;

import org.avaeriandev.titancore.quest.requirements.Requirement;
import org.avaeriandev.titancore.quest.rewards.Reward;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.LogRecord;

public class Quest {

    private String name;
    private List<String> lore;
    private List<? extends Requirement> requirements;
    private List<? extends Reward> rewards;

    public Quest(String name, List<String> lore, List<? extends Requirement> requirements, List<? extends Reward> rewards) {
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

    public List<? extends Requirement> getRequirements() {
        return requirements;
    }

    public List<? extends Reward> getRewards() {
        return rewards;
    }
}
