package com.archetype.architectural.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archetype.architectural.dto.CreateOrderRequest;
import com.archetype.architectural.dto.CreateOrderResponse;
import com.archetype.architectural.dto.domainx.FindByCdtarjQuery;
import com.archetype.architectural.dto.domainx.FindClientStatusAggregateResponse;
import com.archetype.architectural.dto.domainx.FindClientStatusQuery;
import com.archetype.architectural.dto.domainx.FindClientStatusQueryResponse;
import com.archetype.architectural.order.entity.PurchaseOrder;
import com.archetype.architectural.order.service.OrderService;
import com.archetype.architectural.order.service.WindowNService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class WindowNController {

    @Autowired
    private WindowNService service;
    
//	@PreAuthorize("hasAuthority('ROLE_BASIC_USER')")
//	@PreAuthorize("hasRole('ROLE_BASIC_USER')")
//	@PreAuthorize("hasRole('BASIC_USER')")

    @Operation(description = "Return  cliente status bundled into Response", summary ="Return 404 if no data content found")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @PostMapping("/obtenerestadocliente")
    public ResponseEntity<Mono<FindClientStatusQueryResponse>> obtenerEstadoCliente(@RequestBody @Valid FindClientStatusQuery query){
        return new ResponseEntity<>(this.service.obtenerEstadoCliente(query),HttpStatus.OK);
    }

    
    @Operation(description = "Return  cliente status bundled into Response", summary ="Return 404 if no data content found")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})    
//    @PostMapping(value="/obteneragggregate",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @PostMapping(value="/obteneragregate")
    public Mono<ResponseEntity<FindClientStatusAggregateResponse>> obtenerEstadoClienteAggregate(@RequestBody @Valid FindByCdtarjQuery query){
        return this.service.obtenerEstadoClienteAggregate(query)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    


}
