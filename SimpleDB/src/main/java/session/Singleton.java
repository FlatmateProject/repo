package session;

import org.apache.log4j.Logger;

import java.sql.*;

class Singleton {

    private static final Logger log = Logger.getLogger(Singleton.class);

    private static Singleton instance = null;
    private static Connection connection;

    public static Singleton getInstance(DataSource dataSource) {
        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton();
                initializeJDBCConnection(dataSource);
            }
        }
        return instance;
    }

    private static void initializeJDBCConnection(DataSource dataSource) {
        try {
            Class.forName(dataSource.getDriver()).newInstance();
            connection = DriverManager.getConnection(dataSource.getHost() + dataSource.getDatabase(), dataSource.getUser(), dataSource.getPassword());
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
