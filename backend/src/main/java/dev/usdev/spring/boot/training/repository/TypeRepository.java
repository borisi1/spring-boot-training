package dev.usdev.spring.boot.training.repository;

import dev.usdev.spring.boot.training.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
