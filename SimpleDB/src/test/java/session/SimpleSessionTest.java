package session;

import org.testng.annotations.BeforeMethod;

public class SimpleSessionTest {

    SimpleSession simpleSession;

    @BeforeMethod
    public void setUp() {
        DataSource dataSource = new DataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setHost("jdbc:mysql://localhost:3306/");
        dataSource.setDatabase("hotel");
        dataSource.setUser("hotel");
        dataSource.setPassword("hotel_dupe");
        simpleSession = new SimpleSession(dataSource);

    }
}
