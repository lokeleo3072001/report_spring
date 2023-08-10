package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.info_product;
import com.example.demo.repository.productRepository;


@Service
public class productService{
    @Autowired
    private productRepository repo;

    public List<info_product> findProductbyname(String name){
        return repo.findProduct(name);
    }

    public List<info_product> findAllProduct(){
        return repo.findAllProducts();
    }

    public info_product findProduct(Long id){
        return repo.findProductByID(id);
    }

    public void acceptChangeProduct(Long id, String name){
        repo.updateProduct(id, name);
    }

    public void deleteProduct(Long id){
        repo.deleteProduct(id);
    }

    public void newProduct(info_product product){
        repo.save(product);  
    }

}
