package com.gebruderzulfajstock.exceptions;

public class NotEnoughQuantity extends  RuntimeException{
    public NotEnoughQuantity() {
    }

    public NotEnoughQuantity(String message) {
        super(message);
    }
}
