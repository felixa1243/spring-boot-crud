package com.iqbalnetwork.controllers.exceptions;

public class EntityExistException extends RuntimeException{
    public EntityExistException() {
        super("data is exists");
    }
}
