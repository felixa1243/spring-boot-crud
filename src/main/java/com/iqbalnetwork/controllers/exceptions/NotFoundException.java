package com.iqbalnetwork.controllers.exceptions;

public class NotFoundException extends Exception{
    public NotFoundException() {
        super("404 Not found");
    }
}
