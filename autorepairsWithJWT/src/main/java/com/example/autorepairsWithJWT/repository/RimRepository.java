package com.example.autorepairsWithJWT.repository;

import com.example.autorepairsWithJWT.model.entity.RimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RimRepository extends JpaRepository<RimEntity, Long> {
    Optional<RimEntity> findByMetalKindAndInches(String metalKind, String inches);
}
