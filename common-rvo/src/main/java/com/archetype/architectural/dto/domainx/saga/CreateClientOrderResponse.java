package com.archetype.architectural.dto.domainx.saga;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

import com.archetype.architectural.enums.OrderStatus;

@Data
@Builder
@Jacksonized
public class CreateClientOrderResponse {

	private final String clientId;
	private final String userId;
	private final String cardId;
    private OrderStatus status;

}
