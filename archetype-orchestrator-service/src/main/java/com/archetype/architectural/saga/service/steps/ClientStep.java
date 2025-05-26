package com.archetype.architectural.saga.service.steps;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.archetype.architectural.dto.domainx.saga.ClientStatus;
import com.archetype.architectural.dto.domainx.saga.CreateClientCommand;
import com.archetype.architectural.dto.domainx.saga.CreateClientResponse;
import com.archetype.architectural.dto.domainx.saga.DeleteClientCommand;
import com.archetype.architectural.saga.core.WorkflowStep;
import com.archetype.architectural.saga.core.WorkflowStepStatus;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class ClientStep implements WorkflowStep {

    private final WebClient webClient;
    private final CreateClientCommand request;
    private DeleteClientCommand requestDelete;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public ClientStep(WebClient webClient, CreateClientCommand request) {
        this.webClient = webClient;
        this.request = request;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return this.stepStatus;
    }

    @Override
    public Mono<Boolean> process() {
        return this.webClient
                    .post()
                    .uri("/clientes/createclient")
                    .body(BodyInserters.fromValue(this.request))
                    .retrieve()
                    .bodyToMono(CreateClientResponse.class)
                    .doOnNext(r -> this.requestDelete=DeleteClientCommand.builder().clientId(r.getClientId()).build())
                    .map(r -> r.getStatus().equals(ClientStatus.CLIENT_CREATED))
                    .doOnNext(b -> this.stepStatus = b ? WorkflowStepStatus.COMPLETE : WorkflowStepStatus.FAILED)
                    .doOnError(e ->{ this.stepStatus =  WorkflowStepStatus.FAILED;
                     			log.error("erroCreate {}",e);
                    });
                  
    }

    @Override
    public Mono<Boolean> revert() {
        return this.webClient
                .post()
                .uri("/clientes/deleteclient")
                .body(BodyInserters.fromValue(this.requestDelete))
                .retrieve()
                .bodyToMono(Void.class)
                .map(r -> true)
                .onErrorReturn(false);
    }

}
