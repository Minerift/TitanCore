package org.avaeriandev.titancore.quests.util.states;

import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.quests.util.QuestData;

public abstract class State {

    public abstract void getResponse(TitanPlayer titanPlayer, QuestData questData);

}
