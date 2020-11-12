package org.avaeriandev.titancore.quest.states;

import org.avaeriandev.titancore.TitanPlayer;
import org.avaeriandev.titancore.quest.QuestData;

public abstract class State {

    public abstract void getResponse(TitanPlayer titanPlayer, QuestData questData);

}
