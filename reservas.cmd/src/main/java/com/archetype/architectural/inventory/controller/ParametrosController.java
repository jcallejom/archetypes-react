package com.archetype.architectural.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archetype.architectural.dto.domainx.FindParamByCodeQuery;
import com.archetype.architectural.dto.domainx.FindParamQueryResponse;
import com.archetype.architectural.inventory.service.ParametrosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("inventory")
public class ParametrosController {

    @Autowired
    private ParametrosService service;

    @Operation(description = "Return  cliente status bundled into Response", summary ="Return 404 if no data content found")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/obtenerestadocliente")
    public ResponseEntity<FindParamQueryResponse> obtenerEstadoCliente(FindParamByCodeQuery query){
        return new ResponseEntity<>(this.service.obtenerParametro(query),HttpStatus.OK);
    }

}
