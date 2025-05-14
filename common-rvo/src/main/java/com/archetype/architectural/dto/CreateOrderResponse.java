package com.archetype.architectural.dto;

import lombok.Data;

import java.util.UUID;

import com.archetype.architectural.enums.OrderStatus;

@Data
public class CreateOrderResponse {

    private UUID orderId;
    private Integer userId;
    private Integer productId;
    private Double amount;
    private OrderStatus status;

}
