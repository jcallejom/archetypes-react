package com.archetype.architectural.order.service.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.archetype.architectural.dto.domainx.FindReservaQueryResponse;
import com.archetype.base.core.exception.TechnicalRuntimeException;
import com.archetype.base.core.exception.model.GenericError;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
@Slf4j
@Service
public class ReservasClient {
	@Autowired
	@Qualifier("reservas")
	private WebClient webClient;
	
    public Mono<FindReservaQueryResponse>  obtenerEstadoReserva(String cdtarj){
		return webClient
		.get()		
		//.uri("reservas/obtenerestado")
		.uri("obtenerestado/{cdtarj}",cdtarj)
 
//		.header(HttpHeaders.AUTHORIZATION, authHeader)
//        .body(Mono.just(query), FindClientStatusQuery.class)
//        .body(BodyInserters.fromValue(query))
//		.accept(MediaType.APPLICATION_JSON,MediaType.TEXT_EVENT_STREAM)
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.bodyToMono(FindReservaQueryResponse.class)
//		.subscribe(mf ->log.debug("reservas: {}",mf ) )
		.doOnTerminate(() -> log.debug("reservas: {}" ))//mas apropiado para altas y actualizaciones junto con.block()
		.doOnError(e -> log.error("reservas error",e) )
		.onErrorResume(ex ->Mono.error(new TechnicalRuntimeException(GenericError.EXCEPTION_COM_ELEMENT_NOT_FOUND,ex)))
//		.onErrorResume(ex -> Mono.empty())
	    .retry(3)//block y retry no se llevan
	    ;


	}    
}
