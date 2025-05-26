package com.archetype.architectural.dto.domainx.saga;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class CreateClientOrderRequest {
    private final String clientId;
    private final String name;
    private final String surname;
    private final String cdtarjcode;
    private final UUID orderId;
//    private Integer userId;
//    private Integer productId;
    
    

}