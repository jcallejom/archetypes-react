package com.archetype.architectural.order.config;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class TokenFilter implements WebFilter{
	
//	private final TokenHolder tokenHolder;
//	    
//	public TokenFilter(TokenHolder tokenHolder) {
//			this.tokenHolder = tokenHolder;
//		}
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        // Obtener la cabecera Authorization
//        HttpHeaders headers = exchange.getRequest().getHeaders();
//
//        // Verificar si la cabecera existe y contiene el token
//        if (headers.containsKey(HttpHeaders.AUTHORIZATION)) {
//            String authorizationHeaderValue = headers.getFirst(HttpHeaders.AUTHORIZATION);
//
//            // Verificar el formato del token ( "Bearer <token>")
//            if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer ")) {
//                String token = authorizationHeaderValue.substring(7); // Eliminar "Bearer "
//                	tokenHolder.setToken(authorizationHeaderValue);
//
//                return chain.filter(exchange);
//            }
//        }
//
//        // Si no hay token o no es válido, 
//        return chain.filter(exchange);
//    }
    
    public static final String TOKEN_ATTRIBUTE = "authToken"; // Clave para el atributo del token

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();

        if (headers.containsKey(HttpHeaders.AUTHORIZATION)) {
            String authorizationHeaderValue = headers.getFirst(HttpHeaders.AUTHORIZATION);

            if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer ")) {
                String token = authorizationHeaderValue.substring(7);

                // Guardar el token como un atributo del ServerWebExchange pero con el Bearer porque lo pasamos
                exchange.getAttributes().put(TOKEN_ATTRIBUTE, authorizationHeaderValue);

                return chain.filter(exchange);
            }
        }

        return chain.filter(exchange);
    }
}
