package com.archetype.architectural.dto.domainx;

import java.time.LocalDateTime;

import com.archetype.architectural.dto.domainx.enums.TipoParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
//@Jacksonized
public final class FindParamQueryResponse {
    private final String cdTipoParam ;
    private final String dsParam;
    private final String dsValoParam;
    private final TipoParam tipoParam;
    private final LocalDateTime inicioMigracion;
    private final LocalDateTime finalMigracion;

}
