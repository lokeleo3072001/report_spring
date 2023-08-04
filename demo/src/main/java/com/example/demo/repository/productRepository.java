package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.info_product;

import jakarta.transaction.Transactional;

public interface productRepository extends JpaRepository<info_product,Long>{

    @Query(value = "Select * FROM info_product p WHERE p.name_product like %:name%", nativeQuery = true)
    List<info_product> findProduct(@Param("name") String name);    

    @Query(value = "Select * FROM info_product", nativeQuery = true)
    List<info_product> findAllProducts();

    @Query(value = "Select * FROM info_product p WHERE p.id = :id", nativeQuery = true)
    info_product findProductByID(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE info_product p SET p.name_product = :name WHERE p.id = :id", nativeQuery = true)
    void updateProduct(@Param("id") Long id, @Param("name") String name);

    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM info_product WHERE id = :id", nativeQuery = true)
    void deleteProduct(@Param("id") Long id);

}
