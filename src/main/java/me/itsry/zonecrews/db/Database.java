package me.itsry.zonecrews.db;

import me.itsry.zonecrews.models.PlayerClassData;
import org.bukkit.entity.Player;

import java.sql.*;

public class Database {

    private Connection connection;

    public Connection getConnection() throws SQLException {

        if (connection != null) {
            return connection;
        }

        String url = "jdbc:mysql://panel.zoneville.net:3306/s4_zonecrews";
        String user = "u4_Bo9TWPI8d5";
        String password = "ZX@dH^=7PI9DomL+mSJyvzNC";

        this.connection = DriverManager.getConnection(url, user, password);

        System.out.println("[ZoneCrews] Successfully hooked into MySQL.");

        return this.connection;

    }

    public void initializeDatabase() throws SQLException {

        Statement statement = getConnection().createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS crew_data(uuid varchar(36) primary key, class_name varchar(32))";
        statement.execute(sql);

        statement.close();

        System.out.println("[ZoneCrews] Created the data table in MySQL successfully.");

    }

    public PlayerClassData findPlayerClassDataByUUID(String uuid) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM crew_data WHERE uuid = ?");
        statement.setString(1, uuid);
        ResultSet results = statement.executeQuery();

        if (results.next()) {

            String class_name = results.getString("class_name");

            PlayerClassData newData = new PlayerClassData(uuid, class_name);

            statement.close();

            return newData;

        }

        statement.close();

        return null;

    }

    public void createPlayerClassData(PlayerClassData data) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO crew_data(uuid, class_name) VALUES (?, ?)");
        statement.setString(1, data.getUuid());
        statement.setString(2, data.getClass_name());

        statement.executeUpdate();

        statement.close();

    }

    public void updatePlayerClassData(PlayerClassData data) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("UPDATE crew_data SET class_name = ? WHERE uuid = ?");
        statement.setString(1, data.getClass_name());
        statement.setString(2, data.getUuid());

        statement.executeUpdate();

        statement.close();

    }

    public PlayerClassData getPlayerClassDataFromDatabase(Player p) throws SQLException {

        PlayerClassData data = findPlayerClassDataByUUID(p.getUniqueId().toString());

        if (data == null) {

            data = new PlayerClassData(p.getUniqueId().toString(), "None");
            createPlayerClassData(data);
            System.out.println("[ZoneCrews] Successfully created new playerdata in MySQL.");

            return data;

        }

        return data;

    }

    public void deletePlayerClassData(Player p) throws SQLException {

        PlayerClassData data = findPlayerClassDataByUUID(p.getUniqueId().toString());

        if (data != null) {
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM crew_data WHERE uuid = ?");
            statement.setString(1, p.getUniqueId().toString());
            statement.executeUpdate();

            statement.close();
        }

    }

}
