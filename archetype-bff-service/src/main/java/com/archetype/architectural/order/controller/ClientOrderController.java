package com.archetype.architectural.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archetype.architectural.dto.domainx.saga.CreateClientOrderRequest;
import com.archetype.architectural.dto.domainx.saga.CreateClientOrderResponse;
import com.archetype.architectural.order.entity.ClientOrder;
import com.archetype.architectural.order.service.OrderClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class ClientOrderController {

    @Autowired
    private OrderClientService service;
    
//    @Operation(description = "Return all client orders bundled into Response", summary ="Return 204 if no data content found")
//    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
//    @ApiResponse(responseCode = "500", description = "Internal error")})
    @PostMapping("/createclient")
    public Mono<ClientOrder> createNewClientOrder(@RequestBody Mono<CreateClientOrderRequest> mono){
        return mono
                .flatMap(this.service::createNewClientOrder);
    }
//    @Operation(description = "Return all orders bundled into Response", summary ="Return 204 if no data content found")
//    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
//    @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/allorderclients")
    public Flux<CreateClientOrderResponse> getOrders(){
        return this.service.getAll();
    }

}
