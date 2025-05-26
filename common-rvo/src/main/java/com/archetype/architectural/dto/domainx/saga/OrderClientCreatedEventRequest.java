package com.archetype.architectural.dto.domainx.saga;

import java.util.UUID;

import com.archetype.architectural.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class OrderClientCreatedEventRequest {

    private final String clientId;
    private final String name;
    private final String surname;
    private final String cdtarjcode;
    private final UUID orderId;



}
