package com.archetype.architectural.clientes.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.archetype.architectural.dto.InventoryCommandRequest;
import com.archetype.architectural.dto.InventoryCommandResponse;
import com.archetype.architectural.dto.domainx.FindClientByDniQuery;
import com.archetype.architectural.dto.domainx.FindClientByDniQueryResponse;
import com.archetype.architectural.dto.domainx.FindClientStatusQuery;
import com.archetype.architectural.dto.domainx.FindClientStatusQueryResponse;
import com.archetype.architectural.dto.domainx.enums.CanalVenta;
import com.archetype.architectural.dto.domainx.enums.TipoUsuario;
import com.archetype.architectural.enums.InventoryStatus;
import com.archetype.base.core.exception.TechnicalRuntimeException;
import com.archetype.base.core.exception.model.GenericError;

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
    
  	

	public FindClientByDniQueryResponse findClientByDni(FindClientByDniQuery query){
		
		if (new Random().nextInt()%2 ==0) //		if (Math.random()%2 ==0) {
			throw new TechnicalRuntimeException(GenericError.EXCEPTION_COM_ELEMENT_NOT_FOUND);
		
		return FindClientByDniQueryResponse.builder().messaje("all it´s ok").cdClient("4324343").cdTarj("2").migracion(true).tipoUsuario(TipoUsuario.B).canalVenta(CanalVenta.TPV)
	        		.build();
	}
}