package dev.usdev.spring.boot.training.repository;

import dev.usdev.spring.boot.training.entity.Company;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Modifying
    @Query(
            value = "TRUNCATE TABLE TBL_COMPANY",
            nativeQuery = true
    )
    @Transactional
    public void truncateTableCompanyNative();

}
