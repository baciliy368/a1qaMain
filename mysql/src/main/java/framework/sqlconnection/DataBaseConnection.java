package framework.sqlconnection;

import framework.utiles.JsonConfigReader;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DataBaseConnection {
    private static DataBaseConnection instance;
    private static Connection connection;
    private static String url = JsonConfigReader.getJsonParameterValue("url");
    private static String username = JsonConfigReader.getJsonParameterValue("username");
    private static String password = JsonConfigReader.getJsonParameterValue("password");

    private DataBaseConnection() {
        connection = new Sql2o(url, username, password).open();
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataBaseConnection getInstance() {
        if (connection == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }
}
