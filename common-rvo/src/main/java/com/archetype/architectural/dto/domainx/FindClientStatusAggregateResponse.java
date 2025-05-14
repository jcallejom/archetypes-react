package com.archetype.architectural.dto.domainx;

import com.archetype.architectural.dto.domainx.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

//@AllArgsConstructor
@Getter
@Builder
@RequiredArgsConstructor(staticName = "create")
//@Jacksonized
public final class FindClientStatusAggregateResponse {
    private final FindClientStatusBffQueryResponse findClientStatusQueryResponse ;
    private final FindParamQueryResponse findParamQueryResponse;
 
}
