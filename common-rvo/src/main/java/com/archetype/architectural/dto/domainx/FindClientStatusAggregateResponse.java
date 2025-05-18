package com.archetype.architectural.dto.domainx;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//@AllArgsConstructor
@Getter
@Builder
@RequiredArgsConstructor(staticName = "create")
//@Jacksonized
public final class FindClientStatusAggregateResponse {
    private final FindClientByDniQueryResponse findClientStatusQueryResponse ;
    private final FindParamQueryResponse findParamQueryResponse;
    private final FindReservaQueryResponse findReservaQueryResponse;
 
}
