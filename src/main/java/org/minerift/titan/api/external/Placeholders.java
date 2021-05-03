package org.minerift.titan.api.external;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.minerift.titan.TitanProfile;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class Placeholders extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "minerift";
    }

    @Override
    public @NotNull String getAuthor() {
        return "AvaerianDev";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer plr, @NotNull String identifier) {

        TitanProfile titanProfile = TitanProfile.get(plr);
        if(titanProfile == null) return "null";

        switch(identifier) {
            case "tickets": return String.valueOf(titanProfile.getTickets());
        }

        return null;
    }
}
