package me.itsry.zonecrews;

import me.itsry.zonecrews.commands.DeletePlayerData;
import me.itsry.zonecrews.commands.GetClass;
import me.itsry.zonecrews.commands.OpenCrewMenu;
import me.itsry.zonecrews.commands.SetClass;
import me.itsry.zonecrews.db.Database;
import me.itsry.zonecrews.expansions.CrewsExpansion;
import me.itsry.zonecrews.listeners.InventoryClick;
import me.itsry.zonecrews.listeners.InventoryClose;
import me.itsry.zonecrews.listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Main extends JavaPlugin {

    private static Main plugin;

    private Database database;

    @Override
    public void onEnable() {
        System.out.println("[ZoneCrews] Attempting to enable ZoneCrews!");
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        new OpenCrewMenu(this);
        new SetClass(this);
        new GetClass(this);
        new DeletePlayerData(this);
        getServer().getPluginManager().registerEvents(new InventoryClick(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(this), this);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new CrewsExpansion(this).register();
        }

        try {
            this.database = new Database();
            database.initializeDatabase();
        } catch (SQLException e) {
            System.out.println("[ZoneCrews] Unable to connect to MySQL and establish tables.");
            e.printStackTrace();
        }

        System.out.println();
        System.out.println(" _____                    ______                      ");
        System.out.println("/__  /  ____  ____  ___  / ____/_______ _      _______");
        System.out.println("  / /  / __ \\/ __ \\/ _ \\/ /   / ___/ _ \\ | /| / / ___/");
        System.out.println(" / /__/ /_/ / / / /  __/ /___/ /  /  __/ |/ |/ (__  )");
        System.out.println("/____/\\____/_/ /_/\\___/\\____/_/   \\___/|__/|__/____/");
        System.out.println();
        plugin = this;
    }

    public Database getDatabase() {
        return database;
    }

    public static Main getPlugin() {
        return plugin;
    }
}
