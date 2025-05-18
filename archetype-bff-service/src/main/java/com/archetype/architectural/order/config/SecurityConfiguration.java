package com.archetype.architectural.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {
	
	@Value("${management.endpoints.web.cors.allowed-origins:*}")
	private String allowedOrigins;
	@Value("${management.endpoints.web.cors.allowed-methods:*}")
	private String allowedMethods;
	@Value("${management.endpoints.web.cors.allowed-headers:*}")
	private String allowedHeaders;
	@Value("${management.endpoints.web.cors.exposed-headers}")
	private String exposedHeaders;
	@Value("${management.endpoints.web.cors.allow-credentials}")	
	private Boolean allowCredentials;
	@Value("${management.endpoints.web.cors.max-age}")	
	private Long maxAge;

    private final ReactiveJwtAuthenticationTokenConverter reactiveJwtAuthenticationTokenConverter;
//	    private final CorsFilter corsFilter;
	public SecurityConfiguration(ReactiveJwtAuthenticationTokenConverter reactiveJwtAuthenticationTokenConverter) {
	    this.reactiveJwtAuthenticationTokenConverter = reactiveJwtAuthenticationTokenConverter;
	}
	    


	
    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
//    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http ,	Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter) {
        return http
                .authorizeExchange(auth -> auth
                         .pathMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                         .pathMatchers("/webjars/swagger-ui.html", "/webjars/swagger-ui/**", "/webjars/v3/api-docs", "/webjars/v3/api-docs/**").permitAll()	
                         .pathMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()	
                         .anyExchange().authenticated()
                		)
               .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                //==.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(c->c.disable())
                .cors(cors -> cors.configurationSource(corsFilter()))
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(reactiveJwtAuthenticationTokenConverter)))
                
                .build();
    }

	public CorsConfigurationSource  corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin(allowedOrigins);
		config.addAllowedHeader(allowedHeaders);
		config.addAllowedMethod(allowedMethods);
		config.addExposedHeader(exposedHeaders);
		config.setMaxAge(maxAge);
		config.setAllowCredentials(allowCredentials);
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}