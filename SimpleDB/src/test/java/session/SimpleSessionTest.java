package session;

import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SimpleSessionTest {

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
}
