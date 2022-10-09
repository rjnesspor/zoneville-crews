package me.itsry.zonecrews.commands;

import me.itsry.zonecrews.Main;
import me.itsry.zonecrews.menus.SelectCrewMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenCrewMenu implements CommandExecutor {

    private Main plugin;

    public OpenCrewMenu(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("openMenu").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        SelectCrewMenu.openMenu(p);

        return false;
    }

}
