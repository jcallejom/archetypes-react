package com.archetype.architectural.clientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archetype.architectural.clientes.service.ClientesService;
import com.archetype.architectural.dto.domainx.FindClientByDniQuery;
import com.archetype.architectural.dto.domainx.FindClientByDniQueryResponse;
import com.archetype.architectural.dto.domainx.saga.CreateClientCommand;
import com.archetype.architectural.dto.domainx.saga.CreateClientResponse;
import com.archetype.architectural.dto.domainx.saga.DeleteClientCommand;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("clientes")
public class ClientesController {

    @Autowired
    private ClientesService service;

 
    @Operation(description = "Return  cliente status bundled into Response", summary ="Return 404 if no data content found")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @PostMapping("/findclientbydni")
    public ResponseEntity<FindClientByDniQueryResponse> findClientByDni(@RequestBody FindClientByDniQuery query){
        return new ResponseEntity<>(this.service.findClientByDni(query),HttpStatus.OK);
    }

    @PostMapping("/createclient")
    public CreateClientResponse createClient(@RequestBody CreateClientCommand requestDTO){
        return this.service.createClient(requestDTO);
    }

    @DeleteMapping("/deleteclient")
    public void deleteClient(@RequestBody DeleteClientCommand requestDTO){
        this.service.deleteClient(requestDTO);
    }
}
