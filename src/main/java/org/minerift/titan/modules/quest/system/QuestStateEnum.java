package org.minerift.titan.modules.quest.system;

import org.minerift.titan.modules.quest.system.states.CompletedState;
import org.minerift.titan.modules.quest.system.states.InProgressState;
import org.minerift.titan.modules.quest.system.states.InactiveState;
import org.minerift.titan.modules.quest.system.states.State;

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
