package com.archetype.architectural.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archetype.architectural.dto.CreateOrderRequest;
import com.archetype.architectural.dto.CreateOrderResponse;
import com.archetype.architectural.order.entity.PurchaseOrder;
import com.archetype.architectural.order.service.OrderService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService service;
    
//    @Operation(description = "Return all orders bundled into Response", summary ="Return 204 if no data content found")
//    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
//    @ApiResponse(responseCode = "500", description = "Internal error")})
    @PostMapping("/create")
    public Mono<PurchaseOrder> createOrder(@RequestBody Mono<CreateOrderRequest> mono){
        return mono
                .flatMap(this.service::createOrder);
    }
//    @Operation(description = "Return all orders bundled into Response", summary ="Return 204 if no data content found")
//    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
//    @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/all")
    public Flux<CreateOrderResponse> getOrders(){
        return this.service.getAll();
    }

}
