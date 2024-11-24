package com.assignment.productservice.exception;

import lombok.Data;


public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(String message) {
        super(message);
    }
}
