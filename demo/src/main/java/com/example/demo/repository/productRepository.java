package com.example.demo.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Product;

public interface productRepository extends JpaRepository<Product,Long>{

    @Query(value = "SELECT * FROM info_product", nativeQuery = true)
    Page<Product> findAllProduct(Pageable pageable);

    @Query(value = "SELECT * FROM info_product", nativeQuery = true)
    List<Product> RestfulfindAllProduct();

    @Query(value = "SELECT * FROM info_product p WHERE p.name_product = :name", nativeQuery = true)
    Product findProductbyName(String name);

    @Query(value = "Select * FROM info_product p WHERE p.name_product like %:name%", nativeQuery = true)
    Page<Product> findProduct(@Param("name") String name, Pageable pageable);    

    @Query(value = "Select * FROM info_product p WHERE p.id = :id", nativeQuery = true)
    Product findProductByID(@Param("id") Long id);
}
