package com.iqbalnetwork.controllers.exceptions;

public class EmptyFieldException extends Exception{
    public EmptyFieldException() {
        super("required field is empty");
    }
}
