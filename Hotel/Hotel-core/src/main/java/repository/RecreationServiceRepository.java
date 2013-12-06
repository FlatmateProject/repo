package repository;

import entity.RecreationServiceData;
import entity.ReservationData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecreationServiceRepository extends CrudRepository<RecreationServiceData, Long> {

    List<RecreationServiceData> findByReservation(ReservationData reservation);
}