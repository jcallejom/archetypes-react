package com.archetype.architectural.order.config;



import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	
//	@Bean
//	public TokenHolder tokenHolder() {
//	    return new TokenHolder();
//	}
//    
//	@Bean
//	public TokenInterceptor tokenInterceptor(TokenHolder tokenHolder) {
//	        return new TokenInterceptor(tokenHolder);
//	}
	
	//sin holder
	@Bean
	public TokenInterceptor tokenInterceptor() {
	        return new TokenInterceptor();
	}
	@Bean
    @Qualifier("parametros")
    public WebClient parameterClient(@Value("${service.endpoints.parametros}") String endpoint){
        return WebClient.builder()
                .baseUrl(endpoint)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
	@Bean
    @Qualifier("parametrostoken")
    public WebClient parameterClientToken(@Value("${service.endpoints.parametros}") String endpoint,TokenInterceptor tokenInterceptor){
        return WebClient.builder()
                .baseUrl(endpoint)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(tokenInterceptor)
                .build();
    }
    @Bean
    @Qualifier("clientes")
    public WebClient clientClient(@Value("${service.endpoints.clientes}") String endpoint){
        return WebClient.builder()
                .baseUrl(endpoint)
//                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    @Bean
    @Qualifier("reservas")
    public WebClient bookingClient(@Value("${service.endpoints.reservas}") String endpoint){
        return WebClient.builder()
                .baseUrl(endpoint)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    @Bean
    @Qualifier("bff")
    public WebClient bffClient(@Value("http://localhost:8080/order/obtenerestadocliente") String endpoint){
        return WebClient.builder()
                .baseUrl(endpoint)
                .build();
    }
}
