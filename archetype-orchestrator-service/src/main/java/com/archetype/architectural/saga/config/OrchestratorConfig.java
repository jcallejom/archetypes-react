package com.archetype.architectural.saga.config;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.archetype.architectural.dto.OrderCreatedEventRequest;
import com.archetype.architectural.dto.OrderCreatedEventResponse;
import com.archetype.architectural.dto.domainx.saga.OrderClientCreatedEventRequest;
import com.archetype.architectural.dto.domainx.saga.OrderClientCreatedEventResponse;
import com.archetype.architectural.saga.service.OrchestratorClientService;
import com.archetype.architectural.saga.service.OrchestratorService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Configuration
public class OrchestratorConfig {

    @Autowired
    private OrchestratorService orchestratorService;

    @Bean
    public Function<Flux<OrderCreatedEventRequest>, Flux<OrderCreatedEventResponse>> processor(){
        return flux -> flux
                            .flatMap(dto -> this.orchestratorService.orderProduct(dto))
                            .doOnNext(dto -> log.info("Status : " + dto.getStatus()));
    }
    
    @Autowired
    private OrchestratorClientService orchestratorClientService;

    @Bean
    public Function<Flux<OrderClientCreatedEventRequest>, Flux<OrderClientCreatedEventResponse>> processorclient(){
        return flux -> flux
                            .flatMap(dto -> this.orchestratorClientService.orderClient(dto))
                            .doOnNext(dto -> log.info("Status : " + dto.getStatus()));
    }

}
