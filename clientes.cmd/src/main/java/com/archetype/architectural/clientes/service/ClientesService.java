package com.archetype.architectural.clientes.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.archetype.architectural.dto.InventoryCommandRequest;
import com.archetype.architectural.dto.InventoryCommandResponse;
import com.archetype.architectural.dto.domainx.FindClientByDniQuery;
import com.archetype.architectural.dto.domainx.FindClientByDniQueryResponse;
import com.archetype.architectural.dto.domainx.enums.CanalVenta;
import com.archetype.architectural.dto.domainx.enums.TipoUsuario;
import com.archetype.architectural.dto.domainx.saga.ClientStatus;
import com.archetype.architectural.dto.domainx.saga.CreateClientCommand;
import com.archetype.architectural.dto.domainx.saga.CreateClientResponse;
import com.archetype.architectural.dto.domainx.saga.DeleteClientCommand;
import com.archetype.architectural.enums.InventoryStatus;
import com.archetype.base.core.exception.TechnicalRuntimeException;
import com.archetype.base.core.exception.model.GenericError;

import jakarta.annotation.PostConstruct;

@Service
public class ClientesService {
      	
	public FindClientByDniQueryResponse findClientByDni(FindClientByDniQuery query){
		
		if (new Random().nextInt()%2 ==0) //		if (Math.random()%2 ==0) {
			throw new TechnicalRuntimeException(GenericError.EXCEPTION_COM_ELEMENT_NOT_FOUND);
		
		return FindClientByDniQueryResponse.builder().messaje("all it´s ok").cdClient("4324343").cdTarj("2").migracion(true).tipoUsuario(TipoUsuario.B).canalVenta(CanalVenta.TPV)
	        		.build();
	}

	public void deleteClient(DeleteClientCommand requestDTO) {
		// TODO Auto-generated method stub
		
	}

	public CreateClientResponse createClient(CreateClientCommand requestDTO) {
		// TODO Auto-generated method stub
		CreateClientResponse c = CreateClientResponse.builder().clientId(UUID.randomUUID().toString()).status(ClientStatus.CLIENT_CREATED).build();
		clients.put(c.getClientId(),c.getStatus() );
		return CreateClientResponse.builder().clientId(UUID.randomUUID().toString()).status(ClientStatus.CLIENT_CREATED).build();
	}
	
	private Map<String, ClientStatus> clients;

    @PostConstruct
    private void init(){
        this.clients = new HashMap<>();
//        this.clients.put(1, 1000d);
//        this.clients.put(2, 1000d);
//        this.clients.put(3, 1000d);
    }
}