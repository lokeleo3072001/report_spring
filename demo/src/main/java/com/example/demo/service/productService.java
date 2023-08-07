package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.productRepository;


@Service
public class productService{
    @Autowired
    private productRepository repo;

    // public List<Product> findAllProduct(Pageable pageable){
    //     return repo.findAllProduct(pageable);
    // }

    public List<Product> findProductbyname(String name){
        return repo.findProduct(name);
    }

    // public Page<Products> findAllProduct(Pageable pageable){
    //     return repo.findAll(pageable);
    // }

    

    public Product findProduct(Long id){
        return repo.findProductByID(id);
    }

    public void acceptChangeProduct(Long id, String name){
        repo.updateProduct(id, name);
    }

    public void deleteProduct(Long id){
        repo.deleteById(id);;
    }

    public void newProduct(Product product){ 
    }

}
