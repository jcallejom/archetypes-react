package com.archetype.architectural.dto;

import lombok.Data;

import java.util.UUID;

import com.archetype.architectural.enums.OrderStatus;

@Data
public class OrderCreatedEventResponse {

    private Integer userId;
    private Integer productId;
    private UUID orderId;
    private Double amount;
    private OrderStatus status;

}
