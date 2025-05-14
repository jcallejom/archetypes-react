package com.archetype.architectural.order.eventhandlers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.archetype.architectural.dto.OrderCreatedEventRequest;
import com.archetype.architectural.dto.OrderCreatedEventResponse;
import com.archetype.architectural.order.service.OrderEventUpdateService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Configuration
public class OrderEventHandler {

    @Autowired
    private Flux<OrderCreatedEventRequest> flux;

    @Autowired
    private OrderEventUpdateService service;
    
//    static List<PurchaseOrder> orderList = new ArrayList<>();
    static List<OrderCreatedEventRequest> orderList = new ArrayList<>();
    static {
//        orderList.add(new PurchaseOrder(UUID.randomUUID(),1, 3,300.0d,OrderStatus.ORDER_CREATED));
    	OrderCreatedEventRequest requestDTO= new OrderCreatedEventRequest();
        requestDTO.setUserId(1);
        requestDTO.setAmount(100d);
        requestDTO.setOrderId(UUID.randomUUID());
        requestDTO.setProductId(3);
    	orderList.add(requestDTO);
    }

//    @Bean
//    public Supplier<Flux<OrderCreatedEventRequest>> ordersupplier(){
//
//        return () -> Flux.interval(Duration.ofSeconds(5))
//                .map(value -> orderList
//                        .get((int)((Math.abs(orderList.size() - 1)) * Math.random())))
//                .log();
//    }
    @Bean
//    public Supplier<Flux<OrderCreatedEventRequest>> supplier(){
    public Supplier<Flux<OrderCreatedEventRequest>> ordersupplier(){	
        return () -> flux;
    };

    @Bean
//    public Consumer<Flux<OrderCreatedEventResponse>> consumer(){
    public Consumer<Flux<OrderCreatedEventResponse>> orderconsumer(){	
        return f -> f
                .doOnNext(c -> log.info("Consuming :: " + c))
                .flatMap(responseDTO -> this.service.updateOrder(responseDTO))
                .subscribe();
    };

}
