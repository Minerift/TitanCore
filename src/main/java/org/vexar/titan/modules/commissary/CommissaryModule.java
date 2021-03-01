package org.vexar.titan.modules.commissary;

import org.vexar.titan.modules.Module;
import org.vexar.titan.modules.commissary.commands.CommissaryCommand;
import org.vexar.titan.modules.commissary.listeners.CommissarySignListener;

public class CommissaryModule extends Module {

    @Override
    protected void onEnable() {

        registerCommand("commissary", new CommissaryCommand());
        registerListener(new CommissarySignListener());

    }

    @Override
    protected void onDisable() {

    }
}
