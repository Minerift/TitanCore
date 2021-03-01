package org.vexar.titan.modules.commissary.panels;

import org.avaeriandev.api.panel.IconScript;
import org.avaeriandev.api.panel.Panel;
import org.avaeriandev.api.panel.PanelIcon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.vexar.titan.modules.commissary.util.CommissaryTier;
import org.vexar.titan.modules.commissary.util.PurchaseScript;

import java.util.*;

public class CommissaryPanel extends Panel {

    private static final String title = "Commissary";
    private static final int rows = 3;

    public CommissaryPanel() {
        super(title, rows);
        setLayout();
    }

    private void setLayout() {

        Map<Integer, PanelIcon> layout = new HashMap<>();

        PanelIcon purchaseC1 = new PanelIcon("&aConfirm", new ItemStack(Material.WOOL, 1, (byte) 5), new PurchaseScript(CommissaryTier.C1));
        PanelIcon purchaseC2 = new PanelIcon("&aConfirm", new ItemStack(Material.WOOL, 1, (byte) 5), new PurchaseScript(CommissaryTier.C2));
        PanelIcon purchaseC3 = new PanelIcon("&aConfirm", new ItemStack(Material.WOOL, 1, (byte) 5), new PurchaseScript(CommissaryTier.C3));

        PanelIcon cancel = new PanelIcon("&cCancel", new ItemStack(Material.WOOL, 1, (byte) 14), new IconScript() {
            @Override
            public void run(Player plr) {
                plr.closeInventory();
            }
        });

        PanelIcon infoC1 = new PanelIcon("&5C1 Shop", getTicketLore(CommissaryTier.C1), new ItemStack(Material.PAPER, 1), null);
        PanelIcon infoC2 = new PanelIcon("&5C2 Shop", getTicketLore(CommissaryTier.C2), new ItemStack(Material.PAPER, 1), null);
        PanelIcon infoC3 = new PanelIcon("&5C3 Shop", getTicketLore(CommissaryTier.C3), new ItemStack(Material.PAPER, 1), null);

        layout.put(1, purchaseC1);
        layout.put(5, infoC1);
        layout.put(9, cancel);

        layout.put(10, purchaseC2);
        layout.put(14, infoC2);
        layout.put(18, cancel);

        layout.put(19, purchaseC3);
        layout.put(23, infoC3);
        layout.put(27, cancel);

        super.loadLayout(layout);
    }

    private List<String> getTicketLore(CommissaryTier tier) {
        List<String> ticketLore = new ArrayList<>();
        ticketLore.add("&2Temporary access to " + tier.name() + " shop.");
        ticketLore.add("&4Costs " + tier.getTicketPrice() + " tickets.");
        return ticketLore;
    }
}
