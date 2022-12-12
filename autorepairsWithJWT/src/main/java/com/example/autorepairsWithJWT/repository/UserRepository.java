package com.example.autorepairsWithJWT.repository;

import com.example.autorepairsWithJWT.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    boolean existsUserEntityByUsername(String username);

    boolean existsUserEntityByEmail(String email);
}
