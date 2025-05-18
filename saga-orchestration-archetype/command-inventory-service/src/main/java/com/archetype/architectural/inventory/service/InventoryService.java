package com.archetype.architectural.inventory.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.archetype.architectural.dto.InventoryCommandRequest;
import com.archetype.architectural.dto.InventoryCommandResponse;
import com.archetype.architectural.enums.InventoryStatus;

import jakarta.annotation.PostConstruct;

@Service
public class InventoryService {

    private Map<Integer, Integer> productInventoryMap;

    @PostConstruct
    private void init(){
        this.productInventoryMap = new HashMap<>();
        this.productInventoryMap.put(1, 5);
        this.productInventoryMap.put(2, 5);
        this.productInventoryMap.put(3, 2);
    }

    public InventoryCommandResponse deductInventory(final InventoryCommandRequest requestDTO){
        int quantity = this.productInventoryMap.getOrDefault(requestDTO.getProductId(), 0);
        InventoryCommandResponse responseDTO = new InventoryCommandResponse();
        responseDTO.setOrderId(requestDTO.getOrderId());
        responseDTO.setUserId(requestDTO.getUserId());
        responseDTO.setProductId(requestDTO.getProductId());
        responseDTO.setStatus(InventoryStatus.UNAVAILABLE);
        if(quantity > 0){
            responseDTO.setStatus(InventoryStatus.AVAILABLE);
            this.productInventoryMap.put(requestDTO.getProductId(), quantity - 1);
        }
        return responseDTO;
    }

    public void addInventory(final InventoryCommandRequest requestDTO){
        this.productInventoryMap
                .computeIfPresent(requestDTO.getProductId(), (k, v) -> v + 1);
    }

}
