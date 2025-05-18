package com.archetype.architectural.order.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archetype.architectural.dto.domainx.FindByCdtarjQuery;
import com.archetype.architectural.dto.domainx.FindClientByDniQuery;
import com.archetype.architectural.dto.domainx.FindClientByDniQueryResponse;
import com.archetype.architectural.dto.domainx.FindClientStatusAggregateResponse;
import com.archetype.architectural.dto.domainx.FindClientStatusQuery;
import com.archetype.architectural.dto.domainx.FindClientStatusQueryResponse;
import com.archetype.architectural.dto.domainx.FindParamQueryResponse;
import com.archetype.architectural.dto.domainx.FindReservaQueryResponse;
import com.archetype.architectural.dto.domainx.enums.CanalVenta;
import com.archetype.architectural.dto.domainx.enums.Estadoreserva;
import com.archetype.architectural.order.repository.PurchaseOrderRepository;
import com.archetype.architectural.order.service.clients.ClientesClient;
import com.archetype.architectural.order.service.clients.ParametrosClient;
import com.archetype.architectural.order.service.clients.ReservasClient;
import com.archetype.base.core.exception.TechnicalRuntimeException;
import com.archetype.base.core.exception.model.GenericError;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

@Slf4j
@Service
@RequiredArgsConstructor
public class WindowNService {

	

	private final ClientesClient clientesClient;
	
	private final ParametrosClient parametrosClient;

	private final ReservasClient reservasClient;

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
    public Mono<FindClientStatusQueryResponse> obtenerEstadoCliente(FindClientStatusQuery query) {
    	
       	return this.clientesClient.obtenerClienteByDni(toDomainC(query)).flatMap( 
        		c -> { 	//FindClientStatusQueryResponse response =null;
        				var migracion=false;
        			switch (query.getCanalVta()) {
					case CanalVenta.AMV:
						
						if(c.getCanalVenta().equals(CanalVenta.TPV)) {
//							response=this.clone(toResponseC(c), true);
							migracion=true;
						}	
						else
//							Mono.error(new TechnicalRuntimeException(GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT));
							throw new TechnicalRuntimeException(GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT);

					break;
					case CanalVenta.JO:
						if(c.getCanalVenta().equals(CanalVenta.WEB))  {
//							response=this.clone(toResponseC(c), true);
							migracion=true;
						}	
						else
//							Mono.error(new TechnicalRuntimeException(GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT));
							throw new TechnicalRuntimeException(GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT);
					break;

					default:
//						Mono.error(new TechnicalRuntimeException(GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT));
						throw new TechnicalRuntimeException(GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT);
					}
        			
        			
//        			return Mono.just(response);
        			return Mono.just(this.clone(toResponseC(c), migracion));
        		})
       			.doOnNext(c -> 
       				reservasClient.obtenerEstadoReserva(c.getCdTarj()).map(d -> 
       				{	
//       					FindClientStatusQueryResponse response =null;
        				var migracion=true;
       					if(d.getEstadoreserva().equals(Estadoreserva.D))
       						migracion=false;
//       					response= this.clone(c, migracion);
//            			return Mono.just(response);
            			return Mono.just(this.clone(c, migracion));

       					})
       			)
       			.doOnNext(c -> 
       			this.parametrosClient.obtenerParametro().map(p -> 
       				{	
//   					FindClientStatusQueryResponse response =null;
    				var migracion=false;
   					if(LocalDateTime.now().isAfter(p.getInicioMigracion()) && LocalDateTime.now().isBefore(p.getFinalMigracion()))
   						migracion=true;
//   					response= this.clone(c, migracion);
//        			return Mono.just(response);
        			return Mono.just(this.clone(c, migracion));

   					})
       			)

        		;
        		
        		
//        		.flatMapMany(c ->{
//        	
//            this.reservasClient.obtenerEstadoReserva(c.getCdTarj()).m(r ->
//            {
//            	
//            if(r.getEstadoreserva().equals(Estadoreserva.D))
//            	Mono.error(new TechnicalRuntimeException(GenericError.EXCEPTION_COM_ELEMENT_NOT_FOUND));
//            }
//            )

//        } );
        
    }    
    public Mono<FindClientStatusQueryResponse> obtenerEstadoClientex(FindClientStatusQuery query) {
        return this.clientesClient.obtenerClienteByDni(toDomainC(query)).map(this::toResponseC );
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
    public Mono<FindClientStatusAggregateResponse> obtenerEstadoClienteAggregate(FindByCdtarjQuery query){
        return Mono.zip(
                this.clientesClient.obtenerClienteByDni(toDomainC(query)),
                this.parametrosClient.obtenerParametro(),
                this.reservasClient.obtenerEstadoReserva(query.getCdTarj())
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
    private FindClientByDniQuery toDomainC(final FindByCdtarjQuery query){
    	return FindClientByDniQuery.builder().numeroDocumento(query.getNumeroDocumento()).build();
    }
    private FindClientByDniQuery toDomainC(final FindClientStatusQuery query){
    	return FindClientByDniQuery.builder().numeroDocumento(query.getNumeroDocumento()).build();
    }

    private FindClientStatusQueryResponse toResponseC(final FindClientByDniQueryResponse query){
    	return FindClientStatusQueryResponse.builder()
    			.messaje(query.getMessaje())
    			.cdClient(query.getCdClient())
    			.cdTarj(query.getCdTarj())
    			.tipoUsuario(query.getTipoUsuario())
    			.canalVenta(query.getCanalVenta())
    			.build();
    }

    private FindClientStatusQueryResponse clone(final FindClientStatusQueryResponse query,Boolean migracion){
    	return FindClientStatusQueryResponse.builder()
    			.messaje(query.getMessaje())
    			.cdClient(query.getCdClient())
    			.cdTarj(query.getCdTarj())
    			.tipoUsuario(query.getTipoUsuario())
    			.canalVenta(query.getCanalVenta())
    			.migracion(migracion)
    			.build();
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
