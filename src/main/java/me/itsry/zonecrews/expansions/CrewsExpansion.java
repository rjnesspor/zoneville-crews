package me.itsry.zonecrews.expansions;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.itsry.zonecrews.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class CrewsExpansion extends PlaceholderExpansion {

    private final Main plugin;

    public CrewsExpansion(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "ItsRy_";
    }

    @Override
    public String getIdentifier() {
        return "zonecrews";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    public String onRequest(OfflinePlayer player, String params) {

        if (params.equalsIgnoreCase("crew")) {
            String crew_name;

            try {
                crew_name = plugin.getDatabase().getPlayerClassDataFromDatabase((Player) player).getClass_name();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (crew_name.equalsIgnoreCase("hercules")) {
                return "&cHercules";
            } else if (crew_name.equalsIgnoreCase("mercury")) {
                return "&bMercury";
            } else if (crew_name.equalsIgnoreCase("none")) {
                return "None";
            }
        }

        return null;
    }

}
