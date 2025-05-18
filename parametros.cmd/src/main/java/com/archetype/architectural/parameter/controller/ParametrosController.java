package com.archetype.architectural.parameter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archetype.architectural.dto.domainx.FindParamQueryResponse;
import com.archetype.architectural.parameter.service.ParametrosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("parametros")
public class ParametrosController {

    @Autowired
    private ParametrosService service;

	@PreAuthorize("hasAuthority('ROLE_BASIC_USER')")
    @Operation(description = "Return  cliente status bundled into Response", summary ="Return 404 if no data content found")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/obtenerparametro")
    public ResponseEntity<FindParamQueryResponse> obtenerParametroPeriodo(){
        return new ResponseEntity<>(this.service.obtenerParametroPeriodo(),HttpStatus.OK);
    }

}
