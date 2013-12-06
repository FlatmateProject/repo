package repository;

import entity.ReservationData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationData, Long> {

    List<ReservationData> findByPeselId(long clientId);

    List<ReservationData> findByKrsId(long clientId);
}
