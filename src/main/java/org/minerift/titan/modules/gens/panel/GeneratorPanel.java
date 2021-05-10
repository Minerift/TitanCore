package org.minerift.titan.modules.gens.panel;

import org.avaeriandev.api.panel.Panel;
import org.avaeriandev.api.panel.PanelIcon;

import java.util.HashMap;
import java.util.Map;

public class GeneratorPanel extends Panel {

    private static final String TITLE = "Your Generator";
    private static final int ROWS = 3;

    public GeneratorPanel() {
        super(TITLE, ROWS);
        prepareLayout();
    }

    private void prepareLayout() {

        Map<Integer, PanelIcon> layout = new HashMap<>();


        super.loadLayout(layout);

    }

}
