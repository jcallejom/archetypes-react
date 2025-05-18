package com.archetype.architectural.dto.domainx;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Getter
@Builder
@Jacksonized
public final class FindClientByDniQuery {

    private final String numeroDocumento;
//    private final String canalVta;
//    private final Boolean condicionesServicio;

}