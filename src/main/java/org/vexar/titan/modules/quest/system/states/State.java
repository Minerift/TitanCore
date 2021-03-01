package org.vexar.titan.modules.quest.system.states;

import org.vexar.titan.VexarProfile;
import org.vexar.titan.modules.quest.system.QuestData;

public abstract class State {

    public abstract void getResponse(VexarProfile vexarProfile, QuestData questData);

}
