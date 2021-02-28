package org.avaeriandev.titancore.modules.quests.util;

import org.avaeriandev.titancore.modules.quests.util.states.CompletedState;
import org.avaeriandev.titancore.modules.quests.util.states.InProgressState;
import org.avaeriandev.titancore.modules.quests.util.states.InactiveState;
import org.avaeriandev.titancore.modules.quests.util.states.State;

public enum QuestStateEnum {

    COMPLETED_ON_COOLDOWN (new CompletedState()),
    NOT_ACTIVE            (new InactiveState()),
    ACTIVE                (new InProgressState());

    private State state;
    QuestStateEnum(State state) {
        this.state = state;
    }
    public State getHandler() {
        return state;
    }

}
