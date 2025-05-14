package com.archetype.architectural.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.archetype.architectural.dto.domainx.FindClientStatusAggregateResponse;
import com.archetype.architectural.dto.domainx.FindClientStatusBffQuery;
import com.archetype.architectural.dto.domainx.FindClientStatusBffQueryResponse;
import com.archetype.architectural.dto.domainx.FindClientStatusQueryResponse;
import com.archetype.architectural.dto.domainx.FindParamByCodeQuery;
import com.archetype.architectural.dto.domainx.FindParamQueryResponse;
import com.archetype.architectural.order.repository.PurchaseOrderRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Slf4j
@Service
public class WindowNService {

	
//	@Autowired
//	WebClient.Builder builder;
//	
	@Autowired
	@Qualifier("clientes")
	private WebClient clientesClient;
	@Autowired
	@Qualifier("parametros")
	private WebClient parametrosClient;;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

//    @Autowired
//    private Sinks.Many<OrderCreatedEventRequest> sink;
//
//    public Mono<PurchaseOrder> createOrder(CreateOrderRequest createOrderRequest){
//        return this.purchaseOrderRepository.save(this.dtoToEntity(createOrderRequest))
//                .doOnNext(e -> createOrderRequest.setOrderId(e.getId()))
//                .doOnNext(e -> this.emitEvent(createOrderRequest));
////                .doOnError( (Consumer<? super Throwable>) new RuntimeException("error controlado"));
////                .onErrorReturn(null)
//    }

    public Mono<FindClientStatusBffQueryResponse> obtenerEstadoCliente(FindClientStatusBffQuery query, String authHeader) {
        return this.obtenerCliente(query,authHeader);
//                .map(this::entityToDto);
    }
    

    public Mono<FindClientStatusAggregateResponse> obtenerEstadoClienteAggregate(FindClientStatusBffQuery query, String authHeader){
        return Mono.zip(
                this.obtenerCliente(query,authHeader),
                this.obtenerParametro(authHeader)
        		)
        		.map(this::combine);
    }

    private FindClientStatusAggregateResponse combine(Tuple2<FindClientStatusBffQueryResponse, FindParamQueryResponse> tuple){
        return FindClientStatusAggregateResponse.create(
                tuple.getT1(),
                tuple.getT2()
        );
    }
    private Mono<FindClientStatusBffQueryResponse>  obtenerCliente(FindClientStatusBffQuery query,String authHeader){
//		WebClient webClient=builder.build();
		return clientesClient
//		.get().uri("clientes/findclientbydni",query
        
		.post()		
		.uri("clientes/findclientbydni")
//		.header(HttpHeaders.AUTHORIZATION, authHeader)
//        .body(Mono.just(query), FindClientStatusBffQuery.class)
        .body(BodyInserters.fromValue(query))
//		.accept(MediaType.APPLICATION_JSON,MediaType.TEXT_EVENT_STREAM)
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.bodyToMono(FindClientStatusBffQueryResponse.class);

	}
    private Mono<FindParamQueryResponse>  obtenerParametro(String authHeader){
//		WebClient webClient=builder.build();
		return parametrosClient
//		.get().uri("clientes/findclientbydni",query
   
		.get()		
		.uri("parametros/obtenerparametro")
		
//		.header(HttpHeaders.AUTHORIZATION, authHeader)
//        .body(Mono.just(query), FindClientStatusBffQuery.class)
//        .body(BodyInserters.fromValue(query))
//		.accept(MediaType.APPLICATION_JSON,MediaType.TEXT_EVENT_STREAM)
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.bodyToMono(FindParamQueryResponse.class);

	}
    
//    private Flux<Elemento>  obtenerParametro (String url, String tienda){
////		WebClient webClient=builder.build();
//		return clientesClient
//		.get()
//		.uri(url+"/obtenerestadocliente")
//		.accept(MediaType.APPLICATION_JSON,MediaType.TEXT_EVENT_STREAM)
//		.retrieve()
//		.bodyToFlux(FindClientStatusQueryResponse.class)
//		.map(e->{
//			e.setTienda(tienda);
//			return e;
//		});
//	}
    
    
    
//    private void emitEvent(CreateOrderRequest createOrderRequest){
//    	EmitResult result= this.sink.tryEmitNext(this.getOrchestratorRequestDTO(createOrderRequest));
//    	log.info("Estado de emision: {1}",result);
//    }
//
//    private PurchaseOrder dtoToEntity(final CreateOrderRequest dto){
//        PurchaseOrder purchaseOrder = new PurchaseOrder();
//        purchaseOrder.setId(dto.getOrderId());
//        purchaseOrder.setProductId(dto.getProductId());
//        purchaseOrder.setUserId(dto.getUserId());
//        purchaseOrder.setStatus(OrderStatus.ORDER_CREATED);
//        purchaseOrder.setPrice(PRODUCT_PRICE.get(purchaseOrder.getProductId()));
//        return purchaseOrder;
//    }
//
//    private CreateOrderResponse entityToDto(final PurchaseOrder purchaseOrder){
//        CreateOrderResponse dto = new CreateOrderResponse();
//        dto.setOrderId(purchaseOrder.getId());
//        dto.setProductId(purchaseOrder.getProductId());
//        dto.setUserId(purchaseOrder.getUserId());
//        dto.setStatus(purchaseOrder.getStatus());
//        dto.setAmount(purchaseOrder.getPrice());
//        return dto;
//    }
//
//    public OrderCreatedEventRequest getOrchestratorRequestDTO(CreateOrderRequest createOrderRequest){
//        OrderCreatedEventRequest requestDTO = new OrderCreatedEventRequest();
//        requestDTO.setUserId(createOrderRequest.getUserId());
//        requestDTO.setAmount(PRODUCT_PRICE.get(createOrderRequest.getProductId()));
//        requestDTO.setOrderId(createOrderRequest.getOrderId());
//        requestDTO.setProductId(createOrderRequest.getProductId());
//        return requestDTO;
//    }

}
