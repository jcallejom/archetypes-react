package com.archetype.architectural.saga.config;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class TokenInterceptor implements ExchangeFilterFunction  {
	
//    private final TokenHolder tokenHolder;
//    
//	public TokenInterceptor(TokenHolder tokenHolder) {
//		this.tokenHolder = tokenHolder;
//	}
//	@Override
//	public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
//		var	currentToken = tokenHolder.getToken();
//        if (currentToken != null && !currentToken.isEmpty()) {
//            ClientRequest modifiedRequest = ClientRequest.from(request)
//                    .headers(httpHeaders -> httpHeaders.set(HttpHeaders.AUTHORIZATION, currentToken))
//                    .build();
//            return next.exchange(modifiedRequest);
//        } else {
//            return next.exchange(request); // Si no hay token, se pasa la solicitud sin modificar
//        }
//     }
	
	
	//sin holder quitarlo
    public static final String TOKEN_ATTRIBUTE = "authToken"; // Debe ser la misma clave que en AuthorizationFilter
	 @Override
	    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
	        // Necesitamos acceder al ServerWebExchange de alguna manera.
	        // Una forma es a través del contexto Reactor.

	        return Mono.deferContextual(context -> {
	            ServerWebExchange exchange = context.get(ServerWebExchange.class);
	            String currentToken = (String) exchange.getAttributes().get(TOKEN_ATTRIBUTE);

	            if (currentToken != null && !currentToken.isEmpty()) {
	                ClientRequest modifiedRequest = ClientRequest.from(request)
	                        .headers(httpHeaders -> httpHeaders.set(HttpHeaders.AUTHORIZATION, currentToken))
	                        .build();
	                return next.exchange(modifiedRequest);
	            } else {
	                return next.exchange(request); // Si no hay token, se pasa la solicitud sin modificar
	            }
	        });
	    }

}
