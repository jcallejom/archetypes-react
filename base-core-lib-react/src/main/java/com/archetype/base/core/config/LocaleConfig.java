/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.server.i18n.FixedLocaleContextResolver;
import org.springframework.web.server.i18n.LocaleContextResolver;

// TODO: Auto-generated Javadoc
/**
 * Configuration class for Internationalization
 *  ISO639
 *   text_es.properties
 *   text_en.properties
 *  ISO3166
 *   text_es_es.properties
 *   text_es_mx.properties
 * 
 */
@Configuration
public class LocaleConfig extends DelegatingWebFluxConfiguration{ //implements WebFluxConfigurer {
//	@Autowired
//	WebProperties webProperties;
//	/**
//	 * Locale resolver.
//	 *
//	 * @return the locale resolver
//	 */
//	@Bean
//	@ConditionalOnMissingBean(name = WebHttpHandlerBuilder.LOCALE_CONTEXT_RESOLVER_BEAN_NAME)
//	public LocaleContextResolver  localeContextResolver() {
////		 if (this.webProperties.getLocaleResolver() == WebProperties.LocaleResolver.FIXED) {
////		        return new FixedLocaleContextResolver(this.webProperties.getLocale());
////		    }
//	    AcceptHeaderLocaleContextResolver localeContextResolver = new AcceptHeaderLocaleContextResolver();
//		localeContextResolver.setDefaultLocale(Locale.forLanguageTag("es-ES"));
//		return localeContextResolver;		
//	}
	
	/**
	 * an interceptor bean that will switch to a new locale based on the value of
	 * the language parameter appended to a request:.
	 *
	 * @param registry the registry
	 * language should be the name of the request param i.e
	 *           localhost:8081/api/get-greeting?language=fr
	 *           <p>
	 *           Note: All requests to the backend needing Internationalization
	 *           should have the "language" request param
	 */
//	@Override
//	public void addInterceptors(final Interceptor registry) {
//		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//		
//		localeChangeInterceptor.setParamName("language");
//		registry.addInterceptor(localeChangeInterceptor);
//	}

}
