package com.iqbalnetwork.models.responses;

public class ErrorResponse extends CommonResponse {
    public ErrorResponse(int statusCode, String message) {
        super.setStatusCode(statusCode);
        super.setMessage(message);
        super.setStatus("ERROR");
    }
}
