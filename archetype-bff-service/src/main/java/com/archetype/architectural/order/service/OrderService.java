package com.archetype.architectural.order.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archetype.architectural.dto.CreateOrderRequest;
import com.archetype.architectural.dto.CreateOrderResponse;
import com.archetype.architectural.dto.OrderCreatedEventRequest;
import com.archetype.architectural.enums.OrderStatus;
import com.archetype.architectural.order.entity.PurchaseOrder;
import com.archetype.architectural.order.repository.PurchaseOrderRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitResult;

@Slf4j
@Service
public class OrderService {

    // product price map
    private static final Map<Integer, Double> PRODUCT_PRICE =  Map.of(
            1, 100d,
            2, 200d,
            3, 300d
    );

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private Sinks.Many<OrderCreatedEventRequest> sink;

    public Mono<PurchaseOrder> createOrder(CreateOrderRequest createOrderRequest){
        return this.purchaseOrderRepository.save(this.dtoToEntity(createOrderRequest))
                .doOnNext(e -> createOrderRequest.setOrderId(e.getId()))
                .doOnNext(e -> this.emitEvent(createOrderRequest));
//                .doOnError( (Consumer<? super Throwable>) new RuntimeException("error controlado"));
//                .onErrorReturn(null)
    }

    public Flux<CreateOrderResponse> getAll() {
        return this.purchaseOrderRepository.findAll()
                .map(this::entityToDto);
    }

    private void emitEvent(CreateOrderRequest createOrderRequest){
    	EmitResult result= this.sink.tryEmitNext(this.getOrchestratorRequestDTO(createOrderRequest));
    	log.info("Estado de emision: {1}",result);
    }

    private PurchaseOrder dtoToEntity(final CreateOrderRequest dto){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(dto.getOrderId());
        purchaseOrder.setProductId(dto.getProductId());
        purchaseOrder.setUserId(dto.getUserId());
        purchaseOrder.setStatus(OrderStatus.ORDER_CREATED);
        purchaseOrder.setPrice(PRODUCT_PRICE.get(purchaseOrder.getProductId()));
        return purchaseOrder;
    }

    private CreateOrderResponse entityToDto(final PurchaseOrder purchaseOrder){
        CreateOrderResponse dto = new CreateOrderResponse();
        dto.setOrderId(purchaseOrder.getId());
        dto.setProductId(purchaseOrder.getProductId());
        dto.setUserId(purchaseOrder.getUserId());
        dto.setStatus(purchaseOrder.getStatus());
        dto.setAmount(purchaseOrder.getPrice());
        return dto;
    }

    public OrderCreatedEventRequest getOrchestratorRequestDTO(CreateOrderRequest createOrderRequest){
        OrderCreatedEventRequest requestDTO = new OrderCreatedEventRequest();
        requestDTO.setUserId(createOrderRequest.getUserId());
        requestDTO.setAmount(PRODUCT_PRICE.get(createOrderRequest.getProductId()));
        requestDTO.setOrderId(createOrderRequest.getOrderId());
        requestDTO.setProductId(createOrderRequest.getProductId());
        return requestDTO;
    }

}
