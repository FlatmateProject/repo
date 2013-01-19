package session;

public class DataSource {

    private String driver;

    private String database;

    private String user;

    private String password;

    private String host;

    public String getDriver() {
        return driver;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
