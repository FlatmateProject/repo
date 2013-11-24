package repository;

import entity.ReservationData;
import exception.DAOException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<ReservationData, Long> {

    List<ReservationData> findByPeselId(long clientId) throws DAOException;

    List<ReservationData> findByKrsId(long clientId) throws DAOException;
}
