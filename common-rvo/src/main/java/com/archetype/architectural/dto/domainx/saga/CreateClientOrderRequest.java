package com.archetype.architectural.dto.domainx.saga;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class CreateClientOrderRequest {
    private  String clientId;
    private  String name;
    private  String surname;
    private  String cdtarjcode;
    private  UUID orderId;
//    private Integer userId;
//    private Integer productId;
    
    

}