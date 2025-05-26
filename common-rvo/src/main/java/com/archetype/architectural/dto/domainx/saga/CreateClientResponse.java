package com.archetype.architectural.dto.domainx.saga;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

import com.archetype.architectural.enums.PaymentStatus;

@Data
@Builder
@Jacksonized
public final class CreateClientResponse {
    private final String clientId;
    private final ClientStatus status;
    private final String mensaje;
}
