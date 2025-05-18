package com.archetype.architectural.parameter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
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

    private final JwtAuthenticationTokenConverter jwtAuthenticationTokenConverter;
//    private final CorsFilter corsFilter;
    public WebSecurityConfig(JwtAuthenticationTokenConverter jwtAuthenticationTokenConverter) {
        this.jwtAuthenticationTokenConverter = jwtAuthenticationTokenConverter;
    }
    
//	public WebSecurityConfig(JwtAuthenticationTokenConverter jwtAuthenticationTokenConverter, CorsFilter corsFilter) {
//		this.jwtAuthenticationTokenConverter = jwtAuthenticationTokenConverter;
//		this.corsFilter = corsFilter;
//	}
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
//                        .requestMatchers(HttpMethod.GET, "/api/private").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                
                        
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationTokenConverter)))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsFilter()))              
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
