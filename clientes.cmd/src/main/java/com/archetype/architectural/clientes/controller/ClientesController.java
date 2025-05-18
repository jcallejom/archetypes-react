package com.archetype.architectural.clientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archetype.architectural.clientes.service.ClientesService;
import com.archetype.architectural.dto.InventoryCommandRequest;
import com.archetype.architectural.dto.InventoryCommandResponse;
import com.archetype.architectural.dto.domainx.FindClientByDniQuery;
import com.archetype.architectural.dto.domainx.FindClientByDniQueryResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("clientes")
public class ClientesController {

    @Autowired
    private ClientesService service;

    @PostMapping("/deduct")
    public InventoryCommandResponse deduct(@RequestBody final InventoryCommandRequest requestDTO){
        return this.service.deductInventory(requestDTO);
    }

    @PostMapping("/add")
    public void add(@RequestBody final InventoryCommandRequest requestDTO){
        this.service.addInventory(requestDTO);
    }
    @Operation(description = "Return  cliente status bundled into Response", summary ="Return 404 if no data content found")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @PostMapping("/findclientbydni")
    public ResponseEntity<FindClientByDniQueryResponse> findClientByDni(@RequestBody FindClientByDniQuery query){
        return new ResponseEntity<>(this.service.findClientByDni(query),HttpStatus.OK);
    }

}
