package me.itsry.zonecrews.listeners;

import me.itsry.zonecrews.Main;
import me.itsry.zonecrews.menus.SelectCrewMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;

public class InventoryClose implements Listener {

    private Main plugin;

    public InventoryClose(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {

        if (e.getInventory().getName().equalsIgnoreCase("Select Crew")) {

            String class_name = null;
            try {
                class_name = plugin.getDatabase().getPlayerClassDataFromDatabase((Player)e.getPlayer()).getClass_name();
            } catch (SQLException ex) {
                System.out.println("[ZoneCrews] There was an error getting MySQL data.");
                ex.printStackTrace();
            }

            if (class_name.equalsIgnoreCase("none")) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        SelectCrewMenu.openMenu((Player)e.getPlayer());
                    }
                }.runTaskLater(plugin, 1);
            }

        }

    }
}
