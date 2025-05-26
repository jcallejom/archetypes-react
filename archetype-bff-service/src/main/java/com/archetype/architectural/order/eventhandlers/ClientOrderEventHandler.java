package com.archetype.architectural.order.eventhandlers;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.archetype.architectural.dto.domainx.saga.OrderClientCreatedEventRequest;
import com.archetype.architectural.dto.domainx.saga.OrderClientCreatedEventResponse;
import com.archetype.architectural.order.service.OrderClientEventUpdateService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Configuration
public class ClientOrderEventHandler {

    @Autowired
    private Flux<OrderClientCreatedEventRequest> flux;

    @Autowired
    private OrderClientEventUpdateService service;
    

    @Bean
    public Supplier<Flux<OrderClientCreatedEventRequest>> orderclientsupplier(){	
        return () -> flux;
    };

    @Bean
    public Consumer<Flux<OrderClientCreatedEventResponse>> orderclientconsumer(){	
        return f -> f
                .doOnNext(c -> log.info("Consuming :: " + c))
                .flatMap(responseDTO -> this.service.updateOrder(responseDTO))
                .subscribe();
    };

}
