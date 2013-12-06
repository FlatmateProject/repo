package repository;

import entity.CurrencyExchangeData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangeData, Long> {

}