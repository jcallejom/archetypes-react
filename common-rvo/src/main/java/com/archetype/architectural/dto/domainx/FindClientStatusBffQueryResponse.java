package com.archetype.architectural.dto.domainx;

import com.archetype.architectural.dto.domainx.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Getter
@Builder
//@Jacksonized
public final class FindClientStatusBffQueryResponse {
    private final String messaje ;
    private final String cdClient;
    private final String cdTarj;
    private final TipoUsuario tipoUsuario;
    private final Boolean migracion;
}
