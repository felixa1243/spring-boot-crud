package com.iqbalnetwork.models.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class CommonResponse {
    protected int statusCode;
    protected String status;
    protected String message;
}
