package com.archetype.architectural.order.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;

import reactor.core.publisher.Mono;

@Configuration
public class KeycloakConfiguration {

	@Bean
	Converter<Jwt, Collection<GrantedAuthority>> keycloakGrantedAuthoritiesConverter(@Value("${app.security.clientId}") String clientId) {
		return new KeycloakGrantedAuthoritiesConverter(clientId);
	}

	@Bean
	Converter<Jwt, Mono<AbstractAuthenticationToken>> keycloakJwtAuthenticationConverter(Converter<Jwt, Collection<GrantedAuthority>> converter) {
		return new ReactiveJwtAuthenticationTokenConverter(converter);
	}
}
