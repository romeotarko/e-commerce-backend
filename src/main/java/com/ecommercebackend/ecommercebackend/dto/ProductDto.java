package com.ecommercebackend.ecommercebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotBlank(message = "Name field must not be empty")
    private String name;

    @NotBlank(message = "Price field must not be empty")
    private String price;

    @NotBlank(message = "Description field must not be empty")
    private String description;

    @Nullable
    private String unit_in_stock;

    //This constructor is created for Unit Tests purposes
    public ProductDto(String book, String number, String bestBook) {
    }
}
