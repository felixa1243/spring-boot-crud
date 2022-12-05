package com.iqbalnetwork.controllers.exceptions;

import org.springframework.http.HttpStatus;

public class RestTemplateException extends RuntimeException {
    public RestTemplateException(
            String serviceName, HttpStatus statusCode, String error
    ) {
        super(error + " at " + serviceName + "(" + statusCode.value() + ")");
    }
}
