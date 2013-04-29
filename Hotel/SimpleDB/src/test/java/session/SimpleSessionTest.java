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
        DataSource dataSource = new DataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setHost("jdbc:mysql://localhost:3306/");
        dataSource.setDatabase("hotel");
        dataSource.setUser("hotel2");
        dataSource.setPassword("hotel0");

        // when
        simpleSession = new SimpleSession(dataSource);
    }

    @Test
    public void shouldRun() {
        // given
        DataSource dataSource = new DataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setHost("jdbc:mysql://localhost:3306/");
        dataSource.setDatabase("hotel");
        dataSource.setUser("hotel");
        dataSource.setPassword("hotel_dupe");

        // when
        SimpleSession simpleSession = new SimpleSession(dataSource);

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
