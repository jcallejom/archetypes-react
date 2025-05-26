package com.archetype.architectural.dto.domainx.saga;

import java.util.UUID;

import com.archetype.architectural.enums.OrderStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderClientCreatedEventResponse {

    private UUID orderId;
   	private String clientId;
	private String userId;
	private String cardId;
    private OrderStatus status;

}
