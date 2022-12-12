package com.example.autorepairsWithJWT.repository;

import com.example.autorepairsWithJWT.model.entity.TyreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TyreRepository extends JpaRepository<TyreEntity, Long> {
}
