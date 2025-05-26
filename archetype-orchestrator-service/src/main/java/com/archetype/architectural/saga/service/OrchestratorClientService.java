package com.archetype.architectural.saga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.archetype.architectural.dto.domainx.saga.CreateClientCommand;
import com.archetype.architectural.dto.domainx.saga.CreateUserCommand;
import com.archetype.architectural.dto.domainx.saga.OrderClientCreatedEventRequest;
import com.archetype.architectural.dto.domainx.saga.OrderClientCreatedEventResponse;
import com.archetype.architectural.enums.OrderStatus;
import com.archetype.architectural.saga.core.Workflow;
import com.archetype.architectural.saga.core.WorkflowException;
import com.archetype.architectural.saga.core.WorkflowStep;
import com.archetype.architectural.saga.core.WorkflowStepStatus;
import com.archetype.architectural.saga.service.steps.ClientStep;
import com.archetype.architectural.saga.service.steps.KadapterStep;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrchestratorClientService {

    @Autowired
    @Qualifier("clientes")
    private WebClient clientesClient;

    @Autowired
    @Qualifier("keycloack")
    private WebClient keycloackClient;

    public Mono<OrderClientCreatedEventResponse> orderClient(final OrderClientCreatedEventRequest requestDTO){
        Workflow orderWorkflow = this.getOrderWorkflow(requestDTO);
        return Flux.fromStream(() -> orderWorkflow.getSteps().stream())
                .flatMap(WorkflowStep::process)
                .handle(((aBoolean, synchronousSink) -> {
//                	aBoolean?synchronousSink.next(true): synchronousSink.error(new WorkflowException("create order failed!");
                    if(aBoolean)
                        synchronousSink.next(true);
                    else
                        synchronousSink.error(new WorkflowException("create order failed!"));
                }))
                .then(Mono.fromCallable(() -> getResponseDTO(requestDTO, OrderStatus.ORDER_COMPLETED)))
                .onErrorResume(ex -> this.revertOrder(orderWorkflow, requestDTO));

    }

    private Mono<OrderClientCreatedEventResponse> revertOrder(final Workflow workflow, final OrderClientCreatedEventRequest request){
        return Flux.fromStream(() -> workflow.getSteps().stream())
                .filter(wf -> wf.getStatus().equals(WorkflowStepStatus.COMPLETE))
                .flatMap(WorkflowStep::revert)
                .retry(3)
                .then(Mono.just(this.getResponseDTO(request, OrderStatus.ORDER_CANCELLED)));
    }

    private Workflow getOrderWorkflow(OrderClientCreatedEventRequest requestDTO){
        WorkflowStep crearClienteStep = new ClientStep(this.clientesClient, this.getCreateClientCommand(requestDTO));
        WorkflowStep crearUsuarioStep = new KadapterStep(this.keycloackClient, this.getUserCommand(requestDTO));
        return new OrderWorkflow(List.of(crearClienteStep, crearUsuarioStep));
    }

    private OrderClientCreatedEventResponse getResponseDTO(OrderClientCreatedEventRequest request, OrderStatus status){
        
        return OrderClientCreatedEventResponse.builder()
        		.orderId(request.getOrderId())
        		.clientId(request.getClientId())
        		.userId(request.getCdtarjcode())
        		.cardId(request.getCdtarjcode())
        		.status(status)
        		.build();
    }

    private CreateClientCommand getCreateClientCommand(OrderClientCreatedEventRequest request){
            
        return CreateClientCommand.builder()
        		.clientId(request.getClientId())
        		.name(request.getName())
        		.surname(request.getSurname())
        		.build();
    }

    private CreateUserCommand getUserCommand(OrderClientCreatedEventRequest request){
              
        return CreateUserCommand.builder()
        		.username(request.getCdtarjcode())
        		.firstName(request.getName())
        		.lastName(request.getSurname())
        		.password(request.getCdtarjcode())
        		.build();
    }

}
