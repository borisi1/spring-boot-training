package dev.usdev.spring.boot.training.repository;

import dev.usdev.spring.boot.training.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
