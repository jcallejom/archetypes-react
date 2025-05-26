package com.archetype.architectural.saga.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean
	public TokenInterceptor tokenInterceptor() {
	        return new TokenInterceptor();
	}
    @Bean
    @Qualifier("payment")
    public WebClient paymentClient(@Value("${service.endpoints.payment}") String endpoint){
        return WebClient.builder()
                .baseUrl(endpoint)
                .build();
    }

    @Bean
    @Qualifier("inventory")
    public WebClient inventoryClient(@Value("${service.endpoints.inventory}") String endpoint){
        return WebClient.builder()
                .baseUrl(endpoint)
                .build();
    }
    @Bean
    @Qualifier("clientes")
    public WebClient clientClient(@Value("${service.endpoints.clientes}") String endpoint,TokenInterceptor tokenInterceptor){
        return WebClient.builder()
                .baseUrl(endpoint)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(tokenInterceptor)
                .build();
    }
    @Bean
    @Qualifier("keycloack")
    public WebClient keycloackClient(@Value("${service.endpoints.keycloack}") String endpoint,TokenInterceptor tokenInterceptor){
        return WebClient.builder()
                .baseUrl(endpoint)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(tokenInterceptor)
                .build();
    }
    
}
