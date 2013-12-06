package repository;

import entity.CompanyData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyData, Long> {

}