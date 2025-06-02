package com.archetype.architectural.dto.domainx.saga;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public final class DeleteUserCommand {
	private final String username;  
}
