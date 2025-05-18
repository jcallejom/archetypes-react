package com.archetype.architectural.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEventRequest {

    private Integer userId;
    private Integer productId;
    private UUID orderId;
    private Double amount;



}
