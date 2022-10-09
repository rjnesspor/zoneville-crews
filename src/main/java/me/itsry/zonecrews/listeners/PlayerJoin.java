package me.itsry.zonecrews.listeners;

import me.itsry.zonecrews.Main;
import me.itsry.zonecrews.menus.SelectCrewMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;

public class PlayerJoin implements Listener {

    private Main plugin;

    public PlayerJoin(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player p = event.getPlayer();

        for (PotionEffect effect : p.getActivePotionEffects())
            p.removePotionEffect(effect.getType());

        if (!p.hasPlayedBefore()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    SelectCrewMenu.openMenu(p);
                }
            }.runTaskLater(plugin, 5);
        }

        String crew_name = null;

        try {
            crew_name = plugin.getDatabase().getPlayerClassDataFromDatabase(p).getClass_name();
        } catch (SQLException e) {
            System.out.println("[ZoneCrews] There was an error in getting the player's data from MySQL.");
            throw new RuntimeException(e);
        }

        if (crew_name.equalsIgnoreCase("mercury")) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE,0));
        } else if (crew_name.equalsIgnoreCase("hercules")) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE,0));
        }
    }

}
