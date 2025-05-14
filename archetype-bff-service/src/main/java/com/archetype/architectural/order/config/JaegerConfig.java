package com.archetype.architectural.order.config;

import org.springframework.context.annotation.Bean;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;

//@ConditionalOnProperty(prefix = "management.tracing", name = "enabled", havingValue = "false")
//@Configuration
public class JaegerConfig {
//	BraveAutoConfiguration config;
//	
//	OpenTelemetryAutoConfiguration op;
	
//	@Bean
//	OtlpHttpSpanExporter otlpHttpSpanExporter(@Value("${tracing.url}") String url) {
//	    return OtlpHttpSpanExporter.builder()
//	            .setEndpoint(url)
//	            .build();
//	}
	/*Desactiva configuracion tracer por defecto
    @Bean
    Tracer tracer() {
        return Tracer.NOOP;
    }*/
	/*Desactiva configuracion tracer/regesitry por defecto
    @Bean
    ObservationRegistry observationRegistry() {
        return ObservationRegistry.NOOP;
    }*/

//		@Bean
//		public OpenTelemetry openTelemetry() {
//		  return GlobalOpenTelemetry.get();
//		}
//	@Primary
//	@Bean
//	OtlpHttpSpanExporter otlpHttpSpanExporter() {
//	    return OtlpHttpSpanExporter.builder()
//	                .build();
//	}
//	  @Bean
//	    public Sampler buildAlwaysSampler() {
//		  return Sampler.alwaysOn();
//	    }
	/*solo vale con opentelemetry-exporter-zipkin 
	@Bean
	ZipkinSpanExporter zipkinSpanExporter() {
	    return ZipkinSpanExporter.builder()
	                .build();
	}*/
//    @Value("${opentracing.jaeger.enabled:false}")
//    private boolean jaegerEnabled;
//
//    @Value("${opentracing.jaeger.udp-sender.host}")
//    private String opentracingUdpSenderHost;
//
//    @Value("${opentracing.jaeger.udp-sender.port}")
//    private Integer opentracingUdpSenderPort;
//
//    @Bean
//    public Tracer jaegerTracer() {
//
//        if (!jaegerEnabled)
//            return NoopTracerFactory.create();
//
//        JaegerTracer tracer = new io.jaegertracing.Configuration("instrument-list-job")
//                .withSampler(new io.jaegertracing.Configuration.SamplerConfiguration().withType(ConstSampler.TYPE).withParam(1))
//                .withReporter(new io.jaegertracing.Configuration.ReporterConfiguration()
//                        .withSender(new io.jaegertracing.Configuration.SenderConfiguration()
//                                .withAgentHost(opentracingUdpSenderHost).withAgentPort(opentracingUdpSenderPort)).withLogSpans(true)).getTracer();
//
//        GlobalTracer.registerIfAbsent(tracer);
//
//        return tracer;
//    }
}
