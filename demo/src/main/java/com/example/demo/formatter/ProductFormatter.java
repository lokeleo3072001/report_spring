package com.example.demo.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.example.demo.entity.Product;
import com.example.demo.service.productService;

public class ProductFormatter implements Formatter<Product> {

    private productService service;

    public ProductFormatter(productService service){
        this.service = service;
    }

    @Override
    public String print(Product object, Locale locale) {
        throw new UnsupportedOperationException("Unimplemented method 'print'");
    }

    @Override
    public Product parse(String text, Locale locale) throws ParseException {
        return service.findProduct(Long.parseLong(text));
    }
}
