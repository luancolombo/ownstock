package com.lemare.stock.services.Exceptions;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String msg) {
        super(msg);
    }
}
