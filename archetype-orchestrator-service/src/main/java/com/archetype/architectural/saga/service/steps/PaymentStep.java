package com.archetype.architectural.saga.service.steps;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.archetype.architectural.dto.PaymentCommandRequest;
import com.archetype.architectural.dto.PaymentCommandResponse;
import com.archetype.architectural.enums.PaymentStatus;
import com.archetype.architectural.saga.service.WorkflowStep;
import com.archetype.architectural.saga.service.WorkflowStepStatus;

import reactor.core.publisher.Mono;

public class PaymentStep implements WorkflowStep {

    private final WebClient webClient;
    private final PaymentCommandRequest requestDTO;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public PaymentStep(WebClient webClient, PaymentCommandRequest requestDTO) {
        this.webClient = webClient;
        this.requestDTO = requestDTO;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return this.stepStatus;
    }

    @Override
    public Mono<Boolean> process() {
        return this.webClient
                    .post()
                    .uri("/payment/debit")
                    .body(BodyInserters.fromValue(this.requestDTO))
                    .retrieve()
                    .bodyToMono(PaymentCommandResponse.class)
                    .map(r -> r.getStatus().equals(PaymentStatus.PAYMENT_APPROVED))
                    .doOnNext(b -> this.stepStatus = b ? WorkflowStepStatus.COMPLETE : WorkflowStepStatus.FAILED);
    }

    @Override
    public Mono<Boolean> revert() {
        return this.webClient
                .post()
                .uri("/payment/credit")
                .body(BodyInserters.fromValue(this.requestDTO))
                .retrieve()
                .bodyToMono(Void.class)
                .map(r -> true)
                .onErrorReturn(false);
    }

}
