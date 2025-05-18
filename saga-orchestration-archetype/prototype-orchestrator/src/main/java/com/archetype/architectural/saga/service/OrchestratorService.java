package com.archetype.architectural.saga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.archetype.architectural.dto.InventoryCommandRequest;
import com.archetype.architectural.dto.OrderCreatedEventRequest;
import com.archetype.architectural.dto.OrderCreatedEventResponse;
import com.archetype.architectural.dto.PaymentCommandRequest;
import com.archetype.architectural.enums.OrderStatus;
import com.archetype.architectural.saga.service.steps.InventoryStep;
import com.archetype.architectural.saga.service.steps.PaymentStep;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrchestratorService {

    @Autowired
    @Qualifier("payment")
    private WebClient paymentClient;

    @Autowired
    @Qualifier("inventory")
    private WebClient inventoryClient;

    public Mono<OrderCreatedEventResponse> orderProduct(final OrderCreatedEventRequest requestDTO){
        Workflow orderWorkflow = this.getOrderWorkflow(requestDTO);
        return Flux.fromStream(() -> orderWorkflow.getSteps().stream())
                .flatMap(WorkflowStep::process)
                .handle(((aBoolean, synchronousSink) -> {
                    if(aBoolean)
                        synchronousSink.next(true);
                    else
                        synchronousSink.error(new WorkflowException("create order failed!"));
                }))
                .then(Mono.fromCallable(() -> getResponseDTO(requestDTO, OrderStatus.ORDER_COMPLETED)))
                .onErrorResume(ex -> this.revertOrder(orderWorkflow, requestDTO));

    }

    private Mono<OrderCreatedEventResponse> revertOrder(final Workflow workflow, final OrderCreatedEventRequest requestDTO){
        return Flux.fromStream(() -> workflow.getSteps().stream())
                .filter(wf -> wf.getStatus().equals(WorkflowStepStatus.COMPLETE))
                .flatMap(WorkflowStep::revert)
                .retry(3)
                .then(Mono.just(this.getResponseDTO(requestDTO, OrderStatus.ORDER_CANCELLED)));
    }

    private Workflow getOrderWorkflow(OrderCreatedEventRequest requestDTO){
        WorkflowStep paymentStep = new PaymentStep(this.paymentClient, this.getPaymentRequestDTO(requestDTO));
        WorkflowStep inventoryStep = new InventoryStep(this.inventoryClient, this.getInventoryRequestDTO(requestDTO));
        return new OrderWorkflow(List.of(paymentStep, inventoryStep));
    }

    private OrderCreatedEventResponse getResponseDTO(OrderCreatedEventRequest requestDTO, OrderStatus status){
        OrderCreatedEventResponse responseDTO = new OrderCreatedEventResponse();
        responseDTO.setOrderId(requestDTO.getOrderId());
        responseDTO.setAmount(requestDTO.getAmount());
        responseDTO.setProductId(requestDTO.getProductId());
        responseDTO.setUserId(requestDTO.getUserId());
        responseDTO.setStatus(status);
        return responseDTO;
    }

    private PaymentCommandRequest getPaymentRequestDTO(OrderCreatedEventRequest requestDTO){
        PaymentCommandRequest paymentCommandRequest = new PaymentCommandRequest();
        paymentCommandRequest.setUserId(requestDTO.getUserId());
        paymentCommandRequest.setAmount(requestDTO.getAmount());
        paymentCommandRequest.setOrderId(requestDTO.getOrderId());
        return paymentCommandRequest;
    }

    private InventoryCommandRequest getInventoryRequestDTO(OrderCreatedEventRequest requestDTO){
        InventoryCommandRequest inventoryCommandRequest = new InventoryCommandRequest();
        inventoryCommandRequest.setUserId(requestDTO.getUserId());
        inventoryCommandRequest.setProductId(requestDTO.getProductId());
        inventoryCommandRequest.setOrderId(requestDTO.getOrderId());
        return inventoryCommandRequest;
    }

}
