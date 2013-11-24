package repository;

import exception.DAOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import repository.reservation.ReservationRepository;
import spring.ApplicationConfiguration;

@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    public void testShouldWork() throws DAOException {
        //given

        //when
//        reservationRepository.findByPeselId(0);
        //then

    }
}
