package me.itsry.zonecrews.commands;

import me.itsry.zonecrews.Main;
import me.itsry.zonecrews.models.PlayerClassData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class SetClass implements CommandExecutor {

    private Main plugin;

    public SetClass(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("setClass").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);

        try {

            PlayerClassData data = plugin.getDatabase().getPlayerClassDataFromDatabase(target);
            data.setClass_name(args[1]);
            plugin.getDatabase().updatePlayerClassData(data);

        } catch (SQLException e) {
            System.out.println("[ZoneCrews] There was a problem updating MySQL values.");
            e.printStackTrace();
        }

        return false;
    }
}
