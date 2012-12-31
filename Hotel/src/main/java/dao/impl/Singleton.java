package dao.impl;

import org.apache.log4j.Logger;

import java.sql.*;

public class Singleton {

    private static final Logger log = Logger.getLogger(Singleton.class);

    private static Singleton instance = null;
    private static Connection connection;

    public static Singleton getInstance() {
        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton();
                initializeJDBCConnection();
            }
        }
        return instance;
    }

    private static void initializeJDBCConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "hotel", "hotel_dupe");
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage() + "\nBrak połączenia z bazą danych!");
        }
    }

    public ResultSet query(String sql) {
        try {
            log.info(sql);
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean update(String sql) {
        try {
            log.info(sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
