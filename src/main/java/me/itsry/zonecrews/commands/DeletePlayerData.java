package me.itsry.zonecrews.commands;

import me.itsry.zonecrews.Main;
import me.itsry.zonecrews.models.PlayerClassData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.UUID;

public class DeletePlayerData implements CommandExecutor {

    private Main plugin;

    public DeletePlayerData(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("deletePlayerData").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

        try {

            plugin.getDatabase().deletePlayerClassData((Player) target);
            System.out.println("[ZoneCrews] Successfully deleted a MySQL entry.");

        } catch (SQLException e) {
            System.out.println("[ZoneCrews] There was a problem deleting a MySQL entry.");
            e.printStackTrace();
        }

        return false;
    }
}