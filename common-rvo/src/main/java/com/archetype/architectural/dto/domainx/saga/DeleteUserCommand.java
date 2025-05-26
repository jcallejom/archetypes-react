package com.archetype.architectural.dto.domainx.saga;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public final class DeleteUserCommand {
	private final String username;  
}
