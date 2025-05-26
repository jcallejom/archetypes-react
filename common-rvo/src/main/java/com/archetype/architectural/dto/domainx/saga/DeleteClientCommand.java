package com.archetype.architectural.dto.domainx.saga;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public final class DeleteClientCommand {
    private final String clientId; 
  
}
