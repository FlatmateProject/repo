package repository;

import entity.CustomerData;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerData, Long> {

}