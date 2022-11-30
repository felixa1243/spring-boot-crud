package com.iqbalnetwork.models.responses;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SuccessResponse <T> extends CommonResponse {
    private T data;
    public SuccessResponse(String message,T data) {
        super.setStatusCode(200);
        super.setMessage(message);
        super.setStatus("SUCCESS");
        setData(data);
    }
}
