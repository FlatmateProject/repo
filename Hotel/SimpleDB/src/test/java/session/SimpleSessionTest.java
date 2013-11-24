package session;

import exception.DAOException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class SimpleSessionTest {

    SimpleSession simpleSession;

    @BeforeMethod
    public void setUp() {
        SimpleDataSource simpleDataSource = new SimpleDataSource();
        simpleDataSource.setDriver("com.mysql.jdbc.Driver");
        simpleDataSource.setHost("jdbc:mysql://localhost:3306/");
        simpleDataSource.setDatabase("hotel");
        simpleDataSource.setUser("hotel2");
        simpleDataSource.setPassword("hotel0");

        // when
        simpleSession = new SimpleSession(simpleDataSource);
    }

    @Test
    public void shouldRun() {
        // given
        SimpleDataSource simpleDataSource = new SimpleDataSource();
        simpleDataSource.setDriver("com.mysql.jdbc.Driver");
        simpleDataSource.setHost("jdbc:mysql://localhost:3306/");
        simpleDataSource.setDatabase("hotel");
        simpleDataSource.setUser("hotel");
        simpleDataSource.setPassword("hotel_dupe");

        // when
        SimpleSession simpleSession = new SimpleSession(simpleDataSource);

        // then
        assertThat(simpleSession).isNotNull();
    }

    @Test
    public void shouldFindOneCompany() throws DAOException {
        // given
        String query = "select * from hotel.firmy limit 1";

        // when
        List<CompanyData> companyData = simpleSession.executeQuery(query, CompanyData.class);

        // then
        assertThat(companyData)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }
}
