package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.productRepository;


@Service
public class productService{
    @Autowired
    private productRepository repo;


    public Page<Product> findProductbyname(String name, Pageable pageable){
        return repo.findProduct(name, pageable);
    }

    public Product findProductbyName(String name){
        return repo.findProductbyName(name);
    }

    public Page<Product> findAllProduct(Pageable pageable){
        return repo.findAllProduct(pageable);
    }

    public List<Product> restfulFindAllProduct(){
        return repo.RestfulfindAllProduct();
    }

    public Product findProduct(Long id){
        return repo.findProductByID(id);
    }

    // public void acceptChangeProduct(Long id, String name){
    //     repo.updateProduct(id, name);
    // }

    public void deleteProduct(Long id){
        repo.deleteById(id);;
    }

    public Product saveProduct(Product product){
        return repo.save(product);
    }

}
