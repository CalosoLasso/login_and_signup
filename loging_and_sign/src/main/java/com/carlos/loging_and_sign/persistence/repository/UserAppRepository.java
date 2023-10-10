package com.carlos.loging_and_sign.persistence.repository;


import com.carlos.loging_and_sign.persistence.entiy.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserAppRepository extends CrudRepository<UserEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserEntity u WHERE u.userEmail = :email")
    boolean existByEmail(@Param("email") String email);

    @Query("SELECT u FROM UserEntity u WHERE u.userEmail = :email")
    UserEntity findByEmail(@Param("email") String email);

    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserEntity u WHERE u.username = :username")
    boolean existByUsername(@Param("username") String username);
}

