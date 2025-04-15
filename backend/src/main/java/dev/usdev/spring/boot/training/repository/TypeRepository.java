package dev.usdev.spring.boot.training.repository;

import dev.usdev.spring.boot.training.entity.Type;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    @Modifying
    @Query(
            value = "TRUNCATE TABLE TBL_TYPE",
            nativeQuery = true
    )
    @Transactional
    public void truncateTableTypeNative();
}
