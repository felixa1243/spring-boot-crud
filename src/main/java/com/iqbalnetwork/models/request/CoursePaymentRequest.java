package com.iqbalnetwork.models.request;

import lombok.Data;

@Data
public class CoursePaymentRequest {
    private String customerId;
    private String transactionId;
    private String transactionType;
}
