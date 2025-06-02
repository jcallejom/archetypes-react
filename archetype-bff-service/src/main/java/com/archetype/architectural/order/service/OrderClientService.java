package com.archetype.architectural.order.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archetype.architectural.dto.domainx.saga.CreateClientOrderRequest;
import com.archetype.architectural.dto.domainx.saga.CreateClientOrderResponse;
import com.archetype.architectural.dto.domainx.saga.OrderClientCreatedEventRequest;
import com.archetype.architectural.enums.OrderStatus;
import com.archetype.architectural.order.entity.ClientOrder;
import com.archetype.architectural.order.repository.ClientOrderRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Slf4j
@Service
public class OrderClientService {

    @Autowired
    private ClientOrderRepository clientOrderRepository;

    @Autowired
    private Sinks.Many<OrderClientCreatedEventRequest> sink;

    public Mono<ClientOrder> createNewClientOrder(CreateClientOrderRequest createOrderRequest){
        return this.clientOrderRepository.save(this.dtoToEntity(createOrderRequest))
//                .doOnNext(e ->  clone(createOrderRequest ,e.getId()))
        		.doOnNext(e ->  createOrderRequest.setOrderId(e.getId()))
                .doOnNext(e -> this.emitEvent(createOrderRequest));
//                .doOnError( (Consumer<? super Throwable>) new RuntimeException("error controlado"));
//                .onErrorReturn(null);
    }

    public Flux<CreateClientOrderResponse> getAll() {
        return this.clientOrderRepository.findAll()
                .map(this::entityToDto);
    }

    private void emitEvent(CreateClientOrderRequest createOrderRequest){
    	this.sink.tryEmitNext(this.getOrchestratorRequestDTO(createOrderRequest));

//    	EmitResult result= this.sink.tryEmitNext(this.getOrchestratorRequestDTO(createOrderRequest));
//    	log.info("Estado de emision: {1}",result);
    }
    
    private CreateClientOrderRequest clone (CreateClientOrderRequest createOrderRequest, UUID id) {
    	return CreateClientOrderRequest.builder()
    	    	.clientId(createOrderRequest.getClientId())
    	    	.name(createOrderRequest.getName())
    	    	.surname(createOrderRequest.getSurname())
    	    	.cdtarjcode(createOrderRequest.getCdtarjcode())
    	    	.orderId(id)
    	    	.build();
    }
    private ClientOrder dtoToEntity(final CreateClientOrderRequest dto){
    	ClientOrder clientOrder = new ClientOrder();
        clientOrder.setId(dto.getOrderId());
        clientOrder.setClientId(dto.getClientId());
//        clientOrder.setUserId(dto.getCdtarjcode());
        
        clientOrder.setStatus(OrderStatus.ORDER_CREATED);
        return clientOrder;
    }

    private CreateClientOrderResponse entityToDto(final ClientOrder clientOrder){
        
    	return CreateClientOrderResponse.builder()
    	.clientId(clientOrder.getClientId())
    	.userId(clientOrder.getUserId())
    	.cardId(clientOrder.getCardId())
    	.status(clientOrder.getStatus())
    	.build();
    	
    }

    public OrderClientCreatedEventRequest getOrchestratorRequestDTO(CreateClientOrderRequest createOrderRequest){
    	return OrderClientCreatedEventRequest.builder()
    	.clientId(createOrderRequest.getClientId())
    	.name(createOrderRequest.getName())
    	.surname(createOrderRequest.getSurname())
    	.cdtarjcode(createOrderRequest.getCdtarjcode())
    	.orderId(createOrderRequest.getOrderId())
    	.build();

    }

}
