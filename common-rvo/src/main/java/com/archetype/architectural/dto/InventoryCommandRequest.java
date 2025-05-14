package com.archetype.architectural.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class InventoryCommandRequest {

    private Integer userId;
    private Integer productId;
    private UUID orderId;

}
