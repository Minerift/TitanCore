package org.avaeriandev.titancore.quest;

import org.avaeriandev.titancore.quest.states.CompletedState;
import org.avaeriandev.titancore.quest.states.InProgressState;
import org.avaeriandev.titancore.quest.states.InactiveState;
import org.avaeriandev.titancore.quest.states.State;

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
