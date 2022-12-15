package com.example.autorepairsWithJWT.repository;

import com.example.autorepairsWithJWT.model.entity.TyreEntity;
import com.example.autorepairsWithJWT.model.enums.TyreKindEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TyreRepository extends JpaRepository<TyreEntity, Long> {
    Optional<TyreEntity> findByTyreKindAndBrandAndWidthAndHeightAndInchesAndFlat(TyreKindEnum tyreKindEnum, String brand, String width, String height, String inches, String flat);
}
