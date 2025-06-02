package com.archetype.architectural.saga.service.steps;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.archetype.architectural.dto.domainx.saga.ClientStatus;
import com.archetype.architectural.dto.domainx.saga.CreateUserCommand;
import com.archetype.architectural.dto.domainx.saga.CreateUserResponse;
import com.archetype.architectural.dto.domainx.saga.DeleteUserCommand;
import com.archetype.architectural.saga.core.WorkflowStep;
import com.archetype.architectural.saga.core.WorkflowStepStatus;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class KadapterStep implements WorkflowStep {

    private final WebClient webClient;
    private final CreateUserCommand request;
    private DeleteUserCommand requestDelete;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public KadapterStep(WebClient webClient, CreateUserCommand request) {
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
                    .uri("/createuser")
                    .body(BodyInserters.fromValue(this.request))
                    .retrieve()
                    .bodyToMono(CreateUserResponse.class)
                    .doOnNext(r -> this.requestDelete=DeleteUserCommand.builder().username(r.getUsername()).build())
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
                .uri("/deleteuser")
                .body(BodyInserters.fromValue(this.requestDelete))
                .retrieve()
                .bodyToMono(Void.class)
                .map(r -> true)
                .onErrorReturn(false);
    }

}
