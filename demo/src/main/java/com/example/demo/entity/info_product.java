package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="info_product")
public class info_product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=6,max=20,message = "Name should have at least 4 characters and no more than 30 characters!!!")
    private String name_product;

    public Long getID(){
        return this.id;
    }
    public String getName(){
        return this.name_product;
    }

    public void setName(String name){
        this.name_product = name;
    }

    public info_product(String name){
        this.name_product = name;
    }
    public info_product() {
    }
}
