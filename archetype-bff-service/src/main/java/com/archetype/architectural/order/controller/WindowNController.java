package com.archetype.architectural.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archetype.architectural.dto.domainx.FindClientStatusAggregateResponse;
import com.archetype.architectural.dto.domainx.FindClientStatusBffQuery;
import com.archetype.architectural.dto.domainx.FindClientStatusBffQueryResponse;
import com.archetype.architectural.order.service.WindowNService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class WindowNController {

    @Autowired
    private WindowNService service;
    
//	@PreAuthorize("hasAuthority('ROLE_BASIC_USER')")
    @Operation(description = "Return  cliente status bundled into Response", summary ="Return 404 if no data content found")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @PostMapping("/obtenerestadocliente")
    public ResponseEntity<Mono<FindClientStatusBffQueryResponse>> obtenerEstadoCliente(@RequestBody @Valid FindClientStatusBffQuery query,@RequestHeader("Authorization") String authHeader){
        return new ResponseEntity<>(this.service.obtenerEstadoCliente(query,authHeader),HttpStatus.OK);
    }

    
    @GetMapping("/obtenerEstadoClienteAggregate")
    public Mono<ResponseEntity<FindClientStatusAggregateResponse>> obtenerEstadoClienteAggregate(@RequestBody @Valid FindClientStatusBffQuery query,@RequestHeader("Authorization") String authHeader){
        return this.service.obtenerEstadoClienteAggregate(query,authHeader)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
