package com.archetype.architectural.parameter.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.archetype.architectural.dto.domainx.FindParamByCodeQuery;
import com.archetype.architectural.dto.domainx.FindParamQueryResponse;
import com.archetype.architectural.dto.domainx.enums.TipoParam;

@Service
public class ParametrosService {

    
    
    public FindParamQueryResponse obtenerParametroPeriodo(){
        return FindParamQueryResponse.builder().cdTipoParam("COD001").dsParam("parametro 1").dsValoParam("2222").tipoParam(TipoParam.condiciones_ser).inicioMigracion(LocalDateTime.now()).finalMigracion(LocalDateTime.now())
        		.build();
    }
  
}
