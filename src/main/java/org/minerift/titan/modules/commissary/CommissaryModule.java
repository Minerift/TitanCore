package org.minerift.titan.modules.commissary;

import org.minerift.titan.modules.Module;
import org.minerift.titan.modules.commissary.commands.CommissaryCommand;
import org.minerift.titan.modules.commissary.listeners.CommissarySignListener;

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
