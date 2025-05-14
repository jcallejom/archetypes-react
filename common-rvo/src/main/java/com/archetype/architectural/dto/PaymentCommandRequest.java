package com.archetype.architectural.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PaymentCommandRequest {
    private Integer userId;
    private UUID orderId;
    private Double amount;
}
