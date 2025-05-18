package com.archetype.architectural.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.archetype.architectural.dto.domainx.FindByCdtarjQuery;
import com.archetype.architectural.dto.domainx.FindClientStatusAggregateResponse;
import com.archetype.architectural.dto.domainx.FindClientStatusQuery;
import com.archetype.architectural.dto.domainx.FindClientByDniQueryResponse;
import com.archetype.architectural.dto.domainx.FindParamQueryResponse;
import com.archetype.architectural.dto.domainx.FindReservaQueryResponse;
import com.archetype.architectural.order.repository.PurchaseOrderRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

@Slf4j
@Service
public class WindowNService_old {

	
//	@Autowired
//	WebClient.Builder builder;
//	
	
	@Autowired
	@Qualifier("clientes")
	private WebClient clientesClient;
	@Autowired
	@Qualifier("parametros")
	private WebClient parametrosClient;
	@Autowired
	@Qualifier("reservas")
	private WebClient reservasClient;

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
    
// 	  1    verificar canal AMV si canal de venta consultado = TPV , o canal es JO y consultado WEB es true sino false
//    2    parametos si no se cumple la fecha locadate en intervalo error
//    3.   con cdtraj se llama a condultarestado de reserva 
//    cualquiera con error devuelve false
    
    public Mono<FindClientByDniQueryResponse> obtenerEstadoCliente(FindClientStatusQuery query, String authHeader) {
        return this.obtenerCliente(query,authHeader);
//        		.flatMap(e -> return e)
//        		
//        		 
//        		.subscribe();
//                .map(this::entityToDto);
    }
//  private Flux<Elemento>  obtenerParametro (String url, String tienda){
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
//    @KafkaListener(topics = "pedidosTopic",groupId = "myGroup1")
//	public void gestionPedido(Pedido pedido) {
//		productoCodigo(pedido.getCodProducto()) //Mono<Producto>
//		.flatMap(pr->{
//			pr.setStock(pr.getStock()-pedido.getUnidades()!=0?pr.getStock()-pedido.getUnidades():pr.getStock()-pedido.getUnidades());
//			return productosRepository.save(pr); //Mono<Producto>
//		})//Mono<Producto>
//		.subscribe();
//	}
    public Mono<FindClientStatusAggregateResponse> obtenerEstadoClienteAggregate(FindByCdtarjQuery query, String authHeader){
        return Mono.zip(
                this.obtenerCliente(toDomainC(query),authHeader),
                this.obtenerParametro(authHeader),
                this.obtenerReserva(query.getCdTarj(), authHeader)
        		)
        		.map(this::combine);
    }

    private FindClientStatusAggregateResponse combine(Tuple3<FindClientByDniQueryResponse, FindParamQueryResponse,FindReservaQueryResponse> tuple){
        return FindClientStatusAggregateResponse.create(
                tuple.getT1(),
                tuple.getT2(),
                tuple.getT3()
        );
    }
    private FindClientStatusQuery toDomainC(final FindByCdtarjQuery query){
    	return FindClientStatusQuery.builder().numeroDocumento(query.getNumeroDocumento()).build();
    }
    private FindClientStatusQuery toDomainR(final FindByCdtarjQuery query){
    	return FindClientStatusQuery.builder().numeroDocumento(query.getNumeroDocumento()).build();
    }

    private Mono<FindClientByDniQueryResponse>  obtenerCliente(FindClientStatusQuery query,String authHeader){
//		WebClient webClient=builder.build();
		return clientesClient
		.post()	
		.uri("clientes/findclientbydni")
//		.header(HttpHeaders.AUTHORIZATION, authHeader)
//        .body(Mono.just(query), FindClientStatusQuery.class)
        .body(BodyInserters.fromValue(query))
//		.accept(MediaType.APPLICATION_JSON,MediaType.TEXT_EVENT_STREAM)
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.bodyToMono(FindClientByDniQueryResponse.class)
//		.toEntity(FindClientByDniQueryResponse.class)//Mono<ResponseEntity<FindClientByDniQueryResponse>>
		.onErrorResume(ex -> Mono.empty());

	}
    private Mono<FindParamQueryResponse>  obtenerParametro(String authHeader){
		return parametrosClient
		.get()		
		.uri("parametros/obtenerparametro")
//		.header(HttpHeaders.AUTHORIZATION, authHeader)
//        .body(Mono.just(query), FindClientStatusQuery.class)
//        .body(BodyInserters.fromValue(query))
//		.accept(MediaType.APPLICATION_JSON,MediaType.TEXT_EVENT_STREAM)
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.bodyToMono(FindParamQueryResponse.class)
		.onErrorResume(ex -> Mono.empty());

	}
    private Mono<FindReservaQueryResponse>  obtenerReserva(String cdtarj,String authHeader){
		return parametrosClient
		.get()		
		.uri("reservas/obtenerestado")
//		.header(HttpHeaders.AUTHORIZATION, authHeader)
//        .body(Mono.just(query), FindClientStatusQuery.class)
//        .body(BodyInserters.fromValue(query))
//		.accept(MediaType.APPLICATION_JSON,MediaType.TEXT_EVENT_STREAM)
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.bodyToMono(FindReservaQueryResponse.class)
		.onErrorResume(ex -> Mono.empty());

	}    

    
    
    
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
