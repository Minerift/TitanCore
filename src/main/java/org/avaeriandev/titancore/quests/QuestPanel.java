package org.avaeriandev.titancore.quests;

import org.avaeriandev.api.panel.IconScript;
import org.avaeriandev.api.panel.Panel;
import org.avaeriandev.api.panel.PanelIcon;
import org.avaeriandev.api.util.BaseUtils;
import org.avaeriandev.titancore.quest.Quest;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class QuestPanel extends Panel {

    private static final int rows = 3;

    private Quest quest;

    public QuestPanel(Quest quest) {
        super(quest.getName(), rows);
        setupLayout();
    }

    private void setupLayout() {

        Map<Integer, PanelIcon> layout = new HashMap<>();

        layout.put(12, new PanelIcon("&a&lCONFIRM", new ItemStack(Material.EMERALD), new IconScript() {
            @Override
            public void run(Player plr) {
                plr.sendMessage(BaseUtils.chat("&aHello, world!"));
            }
        }));
        layout.put(14, new PanelIcon("&a" + quest.getName(), quest.getLore(), new ItemStack(Material.PAPER), null));
        layout.put(16, new PanelIcon("&c&lCANCEL", new ItemStack(Material.INK_SACK, 1, (short) 14), new IconScript() {
            @Override
            public void run(Player plr) {
                plr.closeInventory();
            }
        }));

        super.loadLayout(layout);
    }

}
