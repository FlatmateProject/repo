package session;

import exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;

class Singleton {

    private static final Logger log = Logger.getLogger(Singleton.class);

    private static Singleton instance;

    private static Connection connection;

    public static Singleton getInstance(SimpleDataSource simpleDataSource) {
        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton();
                initializeJDBCConnection(simpleDataSource);
            }
        }
        return instance;
    }

    private static void initializeJDBCConnection(SimpleDataSource simpleDataSource) {
        try {
            Class.forName(simpleDataSource.getDriver()).newInstance();
            connection = DriverManager.getConnection(simpleDataSource.getHost() + simpleDataSource.getDatabase(), simpleDataSource.getUser(), simpleDataSource.getPassword());
        } catch (Exception e) {
            logExceptionDetails(e);
            log.debug("Brak połączenia z bazą danych!");
            log.debug("datasource details" + simpleDataSource);
        }
    }

    public ResultSet query(String sql) throws DAOException {
        try {
            log.info(sql);
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            logExceptionDetails(e);
            throw new DAOException(e);
        }
    }

    public void update(String sql) throws DAOException {
        try {
            log.info(sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            logExceptionDetails(e);
            throw new DAOException(e);
        }
    }

    private static void logExceptionDetails(Exception e) {
        log.error(e.getClass() + e.getLocalizedMessage());
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            log.error(stackTraceElement);
        }
    }
}
