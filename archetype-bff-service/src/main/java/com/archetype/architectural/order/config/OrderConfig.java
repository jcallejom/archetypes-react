package com.archetype.architectural.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.archetype.architectural.dto.OrderCreatedEventRequest;
import com.archetype.architectural.dto.domainx.saga.OrderClientCreatedEventRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class OrderConfig {

    @Bean
    public Sinks.Many<OrderCreatedEventRequest> sink(){
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Flux<OrderCreatedEventRequest> flux(Sinks.Many<OrderCreatedEventRequest> sink){
        return sink.asFlux();
    }

    @Bean
    public Sinks.Many<OrderClientCreatedEventRequest> sinkc(){
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Flux<OrderClientCreatedEventRequest> fluxc(Sinks.Many<OrderClientCreatedEventRequest> sink){
        return sink.asFlux();
    }
}
