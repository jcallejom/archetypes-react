package com.archetype.architectural.dto.domainx.saga;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public final class CreateUserResponse {
    private final String username;
    private final ClientStatus status;
    private final String mensaje;
    
}
