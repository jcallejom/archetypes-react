package com.archetype.architectural.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archetype.architectural.dto.domainx.saga.OrderClientCreatedEventResponse;
import com.archetype.architectural.order.repository.ClientOrderRepository;

import reactor.core.publisher.Mono;

@Service
public class OrderClientEventUpdateService {

	@Autowired
    private ClientOrderRepository repository;

    public Mono<Void> updateOrder(final OrderClientCreatedEventResponse responseDTO){
        return this.repository.findById(responseDTO.getOrderId())
                .doOnNext(p -> p.setStatus(responseDTO.getStatus()))
                .flatMap(this.repository::save)
                .then();
    }

}
