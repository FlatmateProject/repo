package repository;

import entity.CompanyData;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<CompanyData, Long> {

}