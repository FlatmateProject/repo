package repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import entity.ReservationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import spring.ApplicationConfiguration;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@ContextConfiguration(classes = ApplicationConfiguration.class)
@TestExecutionListeners({DbUnitTestExecutionListener.class})
@DatabaseSetup("/dataset/ReservationRepositoryTest.xml")
@DatabaseTearDown("/dataset/ReservationRepositoryTest_tearDown.xml")
public class ReservationRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ReservationRepository repository;

    @Test
    public void testFindByCustomerId() throws Exception {
        //given
        long peselId = 87122235514L;

        //when
        List<ReservationData> reservations = repository.findByPeselId(peselId);

        //then
        assertThat(reservations)
                .isNotNull()
                .hasSize(1)
                .onProperty("peselId").containsOnly(peselId);
    }

    @Test
    public void testFindByCompanyId() throws Exception {
        //given
        long krsId = 311911L;

        //when
        List<ReservationData> reservations = repository.findByKrsId(krsId);

        //then
        assertThat(reservations)
                .isNotNull()
                .hasSize(1)
                .onProperty("krsId").containsOnly(krsId);
    }
}
