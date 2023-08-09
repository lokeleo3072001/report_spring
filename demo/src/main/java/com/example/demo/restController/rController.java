package com.example.demo.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Product;
import com.example.demo.service.productService;

@RestController
public class rController {

    @Autowired
    public productService service;

    @GetMapping(value = "getListProduct")
    public ResponseEntity<List<Product>> listAllUsers() {
        List<Product> users = service.findAllProduct2();
        if(users.isEmpty()){
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/createNewProduct")
    public ResponseEntity<Void> createProduct(@RequestParam("name") String name){
        Product product = new Product(name);
        if (service.findProductbyName(product.getName()) != null) {
            System.out.println("A User with name " + product.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        service.saveProduct(product);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping(value = "updateProduct")
    public ResponseEntity<Void> updateProduct(@RequestParam("id") Long id, @RequestParam("name") String name){
        Product product = service.findProduct(id);
        product.setName(name);
        service.saveProduct(product);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
