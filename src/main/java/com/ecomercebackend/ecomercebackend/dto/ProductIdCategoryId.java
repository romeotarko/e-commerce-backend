package com.ecomercebackend.ecomercebackend.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ProductIdCategoryId {
    private UUID productId;
    private UUID categoryId;

}