package com.archetype.architectural.order.service.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.archetype.architectural.dto.domainx.FindParamQueryResponse;
import com.archetype.base.core.exception.TechnicalRuntimeException;
import com.archetype.base.core.exception.model.GenericError;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ParametrosClient {
	@Autowired
//	@Qualifier("parametros")
	@Qualifier("parametrostoken")
	private WebClient webClient;
	
	 public Mono<FindParamQueryResponse>  obtenerParametro(){
			return webClient
			.get()		
			.uri("parametros/obtenerparametro")
//			.header(HttpHeaders.AUTHORIZATION, authHeader)
//	        .body(Mono.just(query), FindClientStatusQuery.class)
//	        .body(BodyInserters.fromValue(query))
//			.accept(MediaType.APPLICATION_JSON,MediaType.TEXT_EVENT_STREAM)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(FindParamQueryResponse.class)
			.doOnTerminate(() -> log.debug("parametro: {}" ))//mas apropiado para altas y actualizaciones
			.doOnError(e -> log.error("param error",e) )
//			.onErrorResume(ex -> Mono.empty())
			.onErrorResume(ex ->Mono.error(new TechnicalRuntimeException(GenericError.EXCEPTION_COM_ELEMENT_NOT_FOUND)))
		    .retry(3);


		}
}
