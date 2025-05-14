package com.archetype.architectural.inventory.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.archetype.architectural.dto.domainx.FindParamByCodeQuery;
import com.archetype.architectural.dto.domainx.FindParamQueryResponse;
import com.archetype.architectural.dto.domainx.enums.TipoParam;

@Service
public class ParametrosService {

    
    
    public FindParamQueryResponse obtenerParametro(FindParamByCodeQuery query){
        return FindParamQueryResponse.builder().cdTipoParam("COD001").dsParam("parametro 1").dsValoParam("2222").tipoParam(TipoParam.condiciones_ser).inicioMigracion(LocalDateTime.parse("02-02-2024")).finalMigracion(LocalDateTime.parse("02-02-2024"))
        		.build();
    }
  
}
