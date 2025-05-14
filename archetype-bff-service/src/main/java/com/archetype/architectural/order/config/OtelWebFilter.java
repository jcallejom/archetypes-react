package com.archetype.architectural.order.config;

import java.util.Collections;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.contrib.sampler.RuleBasedRoutingSampler;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.sdk.autoconfigure.spi.AutoConfigurationCustomizerProvider;
import io.opentelemetry.semconv.UrlAttributes;

@Configuration
@Order(Integer.MIN_VALUE)
public class OtelWebFilter {
	/*customize the sampler to exclude health check endpoints from tracing:*/
	 @Bean
	  public AutoConfigurationCustomizerProvider otelCustomizer() {
	    return p ->
	        p.addSamplerCustomizer(
	            (fallback, config) ->
	                RuleBasedRoutingSampler.builder(SpanKind.SERVER, fallback)
	                    .drop(UrlAttributes.URL_PATH, "^/actuator")
	                    .build());
	  }
	 /*This configuration replaces the default OTLP exporter and adds a custom header to the requests.*/
	  @Bean
	  public AutoConfigurationCustomizerProvider otelCustomizerheader() {
	    return p ->
	        p.addSpanExporterCustomizer(
	            (exporter, config) -> {
	              if (exporter instanceof OtlpHttpSpanExporter) {
	                return ((OtlpHttpSpanExporter) exporter)
	                    .toBuilder().setHeaders(this::headers).build();
	              }
	              return exporter;
	            });
	  }

	  private Map<String, String> headers() {
	    return Collections.singletonMap("Authorization", "Bearer " + refreshToken());
	  }

	  private String refreshToken() {
	    // e.g. read the token from a kubernetes secret
	    return "token";
	  }
}
