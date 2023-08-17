package com.example.demo.dao.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min=6,max=20,message = "Name should have at least 6 characters and no more than 20 characters!!!")
    private String name;

    @NotBlank
    @Size(min=4, max= 20, message = "Password too short")
    private String password;

}
