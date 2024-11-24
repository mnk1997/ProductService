package com.assignment.productservice.dto;

import lombok.Data;

@Data
public class FakeStoreProductDto {
    Long id;
    String   title;
    String price;
    String category;
    String description;
    String image;
}
