package ro.ase.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static Logger logger = Logger.getLogger(ConnectionFactory.class.getName());

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    public static void getProperties() {
        Properties properties = new Properties();
        //File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "db.properties");
        ClassLoader classLoader = ConnectionFactory.class.getClassLoader();
        File file = new File(classLoader.getResource("db.properties").getFile());
        try (FileInputStream stream = new FileInputStream(file)) {
            properties.load(stream);
            URL = properties.getProperty("postgresql.jdbcUrl");
            USER = properties.getProperty("postgresql.user");
            PASSWORD = properties.getProperty("postgresql.password");
        } catch (IOException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    public static Connection getConnection() throws SQLException {
        getProperties();
        DriverManager.registerDriver(new org.postgresql.Driver());
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
