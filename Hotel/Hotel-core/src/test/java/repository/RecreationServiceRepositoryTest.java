package repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import entity.RecreationServiceData;
import entity.ReservationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import spring.ApplicationConfiguration;

import java.util.List;

import static assertions.ReservationDataAssertion.assertThat;
import static org.fest.assertions.Assertions.assertThat;

@ContextConfiguration(classes = ApplicationConfiguration.class)
@TestExecutionListeners({DbUnitTestExecutionListener.class})
@DatabaseTearDown("/dataset/RecreationServiceRepositoryTest_tearDown.xml")
public class RecreationServiceRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    RecreationServiceRepository repository;

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    @DatabaseSetup("/dataset/RecreationServiceRepositoryTest_shouldFindByReservationId.xml")
    public void shouldFindByReservationId() throws Exception {
        //given
        long reservationId = 123456L;

        ReservationData reservationData = reservationRepository.findOne(reservationId);

        //when
        List<RecreationServiceData> recreations = repository.findByReservation(reservationData);

        //then
        assertThat(recreations)
                .isNotNull()
                .hasSize(1);
        assertThat(recreations.get(0).getReservation())
                .isNotNull()
                .hasReservationId(reservationId);
    }
}
