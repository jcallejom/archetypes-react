package com.archetype.architectural.dto.domainx;

import com.archetype.architectural.dto.domainx.enums.Estadoreserva;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Getter
@Builder
@Jacksonized
public final class FindReservaQueryResponse {
	private final String id;
	private final String cdtarj;
    private final Estadoreserva estadoreserva ;


}
