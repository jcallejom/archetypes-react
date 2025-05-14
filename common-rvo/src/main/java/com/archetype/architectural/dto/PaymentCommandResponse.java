package com.archetype.architectural.dto;

import lombok.Data;

import java.util.UUID;

import com.archetype.architectural.enums.PaymentStatus;

@Data
public class PaymentCommandResponse {
    private Integer userId;
    private UUID orderId;
    private Double amount;
    private PaymentStatus status;
}
