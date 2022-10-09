package me.itsry.zonecrews.listeners;

import me.itsry.zonecrews.Main;
import me.itsry.zonecrews.models.PlayerClassData;
import me.itsry.zonecrews.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.SQLException;

public class InventoryClick implements Listener {

    private Main plugin;

    public InventoryClick(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (e.getInventory().getName().equalsIgnoreCase("Select Crew")) {

            e.setCancelled(true);

            if (e.getCurrentItem() != null) {

                if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("HERCULES Crew")) {

                    Player p = (Player) e.getWhoClicked();

                    try {
                        PlayerClassData data = plugin.getDatabase().getPlayerClassDataFromDatabase(p);
                        data.setClass_name("Hercules");
                        plugin.getDatabase().updatePlayerClassData(data);
                    } catch (SQLException ex) {
                        System.out.println("[ZoneCrews] There was an error updating a player's class in MySQL.");
                        ex.printStackTrace();
                    }

                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE,0));
                    p.sendMessage(Util.chat("&a&l[!] &7Success, you have chosen the &cHercules &7Crew!"));
                    p.closeInventory();

                } else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("MERCURY Crew")) {

                    Player p = (Player) e.getWhoClicked();

                    try {
                        PlayerClassData data = plugin.getDatabase().getPlayerClassDataFromDatabase(p);
                        data.setClass_name("Mercury");
                        plugin.getDatabase().updatePlayerClassData(data);
                    } catch (SQLException ex) {
                        System.out.println("[ZoneCrews] There was an error updating a player's class in MySQL.");
                        ex.printStackTrace();
                    }

                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE,0));
                    p.sendMessage(Util.chat("&a&l[!] &7Success, you have chosen the &bMercury &7Crew!"));
                    p.closeInventory();

                }
            }
        }
    }

}
