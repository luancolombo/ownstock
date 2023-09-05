package com.lemare.stock.services.Exceptions;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(Object id) {
        super("Category not found. Id: " +id);
    }
}
