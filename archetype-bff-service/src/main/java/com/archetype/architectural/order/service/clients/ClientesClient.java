package com.archetype.architectural.order.service.clients;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.archetype.architectural.dto.domainx.FindClientByDniQuery;
import com.archetype.architectural.dto.domainx.FindClientByDniQueryResponse;
import com.archetype.base.core.exception.TechnicalRuntimeException;
import com.archetype.base.core.exception.model.GenericError;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Slf4j
@Service
public class ClientesClient {
	
	@Autowired
	@Qualifier("clientes")
	private  WebClient webClient;
	
    public Mono<FindClientByDniQueryResponse>  obtenerClienteByDni(FindClientByDniQuery query){
//		WebClient webClient=builder.build();
		return webClient
		.post()	
		.uri("clientes/findclientbydni")
//        .body(Mono.just(query), FindClientStatusQuery.class)
        .body(BodyInserters.fromValue(query))
//		.accept(MediaType.APPLICATION_JSON,MediaType.TEXT_EVENT_STREAM)
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
//		.onStatus(h->h.is4xxClientError(), t->{
		.onStatus(h->h.isSameCodeAs(HttpStatus.NOT_FOUND), t->{
	 
			log.debug("no se ha encontrado cliente {}",query.getNumeroDocumento());
//			return Mono.empty();
//			return Mono.just(FindClientByDniQueryResponse.builder().build());//esto no va a funcionar ¿porque? porque tiene que ser un trownable
			return 	Mono.error(new TechnicalRuntimeException(GenericError.EXCEPTION_COM_ELEMENT_NOT_FOUND));

		})//el do error entra con el retry 
		.bodyToMono(FindClientByDniQueryResponse.class)
//		.toEntity(FindClientByDniQueryResponse.class)//Mono<ResponseEntity<FindClientByDniQueryResponse>
//		.timeout(Duration.ofSeconds(5));//se puede introducir un tiempo de espera acabado el cual lanza error
//		.subscribe(mf ->log.debug("reservas: {}",mf ) );//devuelve un Disposable OJO cuando usarlo y porque
		.switchIfEmpty(Mono.just(FindClientByDniQueryResponse.builder().build()).map(fc ->
				{	
					log.debug("no se ha encontrado cliente {}",query.getNumeroDocumento());
//					Mono.error(new TechnicalRuntimeException(GenericError.EXCEPTION_COM_ELEMENT_NOT_FOUND));

					return fc;
				}
				))//se usa cuando se acuerda en los flujos devolver Mono/flux !!!vacios!!!
//		.block;
		.doOnTerminate(() -> log.debug("clientes TomaToma: {}" ))//mas apropiado para altas y actualizaciones
		.doOnSuccess(cf -> log.debug("clientes TomaToma: {}",cf ))//mas apropiado para consultas

		//		.onErrorResume(ex -> Mono.empty())// hazcer cualquier otra cosa
//		.onErrorReturn(ex ->  new TechnicalRuntimeException(null,ex))
		.doOnError(e ->
			{
				log.error("clientes error",e);
				throw new TechnicalRuntimeException(GenericError.EXCEPTION_COM_ELEMENT_NOT_FOUND,e);
			})
		.retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(1)));//reinrenta3 en intervalo s de 2 seg
//	    .retry(3);
    }
    
//	monoFind.subscribe(p->System.out.println(p));
//	monoFind.switchIfEmpty(Mono.just(new Producto()).map(p->
//			{
//				System.out.println("No se ha encontrado producto");
//				return p;
//			}
//		)).block();
	
}
