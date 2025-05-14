package com.archetype.architectural.dto;

import lombok.Data;

import java.util.UUID;

import com.archetype.architectural.enums.InventoryStatus;

@Data
public class InventoryCommandResponse {

    private UUID orderId;
    private Integer userId;
    private Integer productId;
    private InventoryStatus status;

}
