package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Product;

import jakarta.transaction.Transactional;

public interface productRepository extends JpaRepository<Product,Long>{

    @Query("SELECT p FROM Product p")
    List<Product> findAllProduct(PageRequest pagerequest);

    @Query(value = "Select * FROM info_product p WHERE p.name_product like %:name%", nativeQuery = true)
    List<Product> findProduct(@Param("name") String name);    

    @Query(value = "Select * FROM info_product p WHERE p.id = :id", nativeQuery = true)
    Product findProductByID(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE info_product p SET p.name_product = :name WHERE p.id = :id", nativeQuery = true)
    void updateProduct(@Param("id") Long id, @Param("name") String name);
    
}
