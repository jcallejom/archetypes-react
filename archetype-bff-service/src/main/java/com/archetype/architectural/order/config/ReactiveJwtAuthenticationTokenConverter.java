package com.archetype.architectural.order.config;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ReactiveJwtAuthenticationTokenConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

//    private  final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
	@Value("${jwt.auth.converter.resource-id}")
    private String resourceId;

    @Value("${jwt.auth.converter.principal-attribute}")
    private String principalAttribute;
    
    private  final Converter<Jwt, Flux<GrantedAuthority>> jwtGrantedAuthoritiesConverter ;
    
    public ReactiveJwtAuthenticationTokenConverter(Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter) {
		Assert.notNull(jwtGrantedAuthoritiesConverter, "jwtGrantedAuthoritiesConverter cannot be null");
		this.jwtGrantedAuthoritiesConverter = new ReactiveJwtGrantedAuthoritiesConverterAdapter(jwtGrantedAuthoritiesConverter);
	}

//    public ReactiveJwtAuthenticationTokenConverter(Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter) {
//		Assert.notNull(jwtGrantedAuthoritiesConverter, "jwtGrantedAuthoritiesConverter cannot be null");
//		this.jwtGrantedAuthoritiesConverter = new ReactiveJwtGrantedAuthoritiesConverterAdapter(jwtGrantedAuthoritiesConverter);
//	}
    @Override
    public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {
//        Collection<GrantedAuthority> authorities =
//                Stream.concat(jwtGrantedAuthoritiesConverter.convert(jwt).toStream(), extractResourceRoles(jwt).stream())
//                        .collect(Collectors.toSet());
        
//        String claimName = principalAttribute == null ? JwtClaimNames.SUB : principalAttribute;
//        String claimName = jwt.containsClaim(principalAttribute) ? jwt.getClaimAsString(principalAttribute):JwtClaimNames.SUB ;
        String claimName = principalAttribute == null ?JwtClaimNames.SUB:jwt.getClaimAsString(principalAttribute) ;

//        
//        return Mono.just( new JwtAuthenticationToken(jwt, authorities, jwt.getClaim(claimName)));
        
        return this.jwtGrantedAuthoritiesConverter.convert(jwt)
				.collectList()
				.map((authorities) -> new JwtAuthenticationToken(jwt, authorities, jwt.getClaim(claimName)));
        
    }
    @SuppressWarnings("unchecked")
    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        Map<String, Object> resource;
        Collection<String> resourceRoles;
        if (resourceAccess == null
                || (resource = (Map<String, Object>) resourceAccess.get(resourceId)) == null
                || (resourceRoles = (Collection<String>) resource.get("roles")) == null) {
            return Collections.emptySet();
        }
        return resourceRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
}
