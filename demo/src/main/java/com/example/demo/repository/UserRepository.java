package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    @Query(value = "SELECT * FROM user u WHERE u.name = :name and u.password = :password", nativeQuery = true)
    UserEntity findUserbyNameandPassword(@Param("name") String name, @Param("password") String password);

    @Query(value = "SELECT * FROM user u WHERE u.name = :name", nativeQuery = true)
    Optional<UserEntity> findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM user u WHERE u.name = :name", nativeQuery = true)
    Optional<UserEntity> OptionaluserbyName(@Param("name") String name);
    
}
