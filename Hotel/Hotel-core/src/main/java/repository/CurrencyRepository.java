package repository;

import entity.CurrencyData;
import org.springframework.data.repository.CrudRepository;
import service.cantor.CURRENCY;

public interface CurrencyRepository extends CrudRepository<CurrencyData, Long> {

    CurrencyData findByName(CURRENCY currency);
}
