package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.info_product;

@Repository
public interface productRepository extends JpaRepository<info_product,Long>{

    @Query(value = "Select p FROM info_product WHERE p.name_product like %?1%", nativeQuery = true)
    List<info_product> findProduct(String name);    

    @Query(value = "Select * FROM info_product", nativeQuery = true)
    List<info_product> findAllProducts();

    @Query(value = "Select c FROM info_product WHERE c.id = ?1", nativeQuery = true)
    info_product findProductByID(Long id);
}
