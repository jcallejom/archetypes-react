package com.archetype.architectural.dto.domainx;

import lombok.Data;

import java.util.UUID;

@Data
public final class FindClientStatusQuery {

    private final String numeroDocumento;
    private final String canalVta;
    private final Boolean condicionesServicio;

}