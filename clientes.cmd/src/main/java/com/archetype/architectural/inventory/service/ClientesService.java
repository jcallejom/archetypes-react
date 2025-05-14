package com.archetype.architectural.inventory.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.archetype.architectural.dto.InventoryCommandRequest;
import com.archetype.architectural.dto.InventoryCommandResponse;
import com.archetype.architectural.dto.domainx.FindClientStatusQuery;
import com.archetype.architectural.dto.domainx.FindClientStatusQueryResponse;
import com.archetype.architectural.dto.domainx.enums.TipoUsuario;
import com.archetype.architectural.enums.InventoryStatus;

import jakarta.annotation.PostConstruct;

@Service
public class ClientesService {

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
    
    public FindClientStatusQueryResponse obtenerEstadoCliente(FindClientStatusQuery query){
		return findClientByDni(query);
	}

	

	public FindClientStatusQueryResponse findClientByDni(FindClientStatusQuery query){
        return FindClientStatusQueryResponse.builder().messaje("all it´s ok").cdClient("4324343").cdTarj("2222").migracion(true).tipoUsuario(TipoUsuario.B)
        		.build();
    }
}
