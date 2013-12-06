package repository;

import entity.CustomerData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerData, Long> {

}