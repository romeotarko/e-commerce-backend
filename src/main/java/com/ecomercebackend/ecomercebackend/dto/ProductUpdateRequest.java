package com.ecomercebackend.ecomercebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {

    @NotBlank(message = "Name field must not be empty")
    private String name;

    @NotBlank(message = "Price field must not be empty")
    private String price;

    @NotBlank(message = "Description field must not be empty")
    private String description;

    @NotBlank(message = "Unit in stock field must not be empty")
    private String unit_in_stock;

}