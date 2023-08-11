package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;

public interface userRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM user u WHERE u.name = :name and u.password = :password", nativeQuery = true)
    User findUserbyNameandPassword(@Param("name") String name, @Param("password") String password);

    @Query(value = "SELECT * FROM user u WHERE u.name = :name", nativeQuery = true)
    User findbyName(@Param("name") String name);
    
}
