package org.minerift.titan.modules.quest.system.states;

import org.minerift.titan.TitanProfile;
import org.minerift.titan.modules.quest.system.QuestData;

public abstract class State {

    public abstract void getResponse(TitanProfile titanProfile, QuestData questData);

}
