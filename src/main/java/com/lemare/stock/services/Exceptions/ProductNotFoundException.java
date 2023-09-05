package com.lemare.stock.services.Exceptions;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Object id) {
        super("Product not found. Id: " +id);
    }
}
