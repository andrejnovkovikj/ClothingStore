package com.example.store.model.exceptions;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(Long id) {
        super(String.format("Product with id: %d was not found", id));
    }

}
