package com.archetype.architectural.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archetype.architectural.dto.InventoryCommandRequest;
import com.archetype.architectural.dto.InventoryCommandResponse;
import com.archetype.architectural.inventory.service.InventoryService;

@RestController
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @PostMapping("/deduct")
    public InventoryCommandResponse deduct(@RequestBody final InventoryCommandRequest requestDTO){
        return this.service.deductInventory(requestDTO);
    }

    @PostMapping("/add")
    public void add(@RequestBody final InventoryCommandRequest requestDTO){
        this.service.addInventory(requestDTO);
    }

}
