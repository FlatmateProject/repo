package repository;

import entity.CurrencyData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyData, Long> {

    CurrencyData findByName(String currency);
}
