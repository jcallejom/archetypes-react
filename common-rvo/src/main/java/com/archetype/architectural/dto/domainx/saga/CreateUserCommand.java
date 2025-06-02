package com.archetype.architectural.dto.domainx.saga;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public final class CreateUserCommand {
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String clientId;//nombre del cliente
    
}
