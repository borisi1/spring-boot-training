package dev.usdev.spring.boot.training.repository;

import dev.usdev.spring.boot.training.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    @Modifying
    @Query(
            value = "TRUNCATE TABLE TBL_USER",
            nativeQuery = true
    )
    @Transactional
    public void truncateTableUserNative();

    @Transactional
    void deleteByUserName(String userName);
}
