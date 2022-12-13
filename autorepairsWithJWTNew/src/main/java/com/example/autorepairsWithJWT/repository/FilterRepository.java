package com.example.autorepairsWithJWT.repository;

import com.example.autorepairsWithJWT.model.entity.FilterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterRepository extends JpaRepository<FilterEntity, Long> {
}
