package repository;

import entity.ExchangeCalculationData;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeCalculationRepository extends CrudRepository<ExchangeCalculationData, Long> {

}