package com.archetype.architectural.saga.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;

@Configuration
public class JaegerConfig {
	
//	@Bean
//	OtlpHttpSpanExporter otlpHttpSpanExporter(@Value("${tracing.url}") String url) {
//	    return OtlpHttpSpanExporter.builder()
//	            .setEndpoint(url)
//	            .build();
//	}
//	@Bean
//	OtlpHttpSpanExporter otlpHttpSpanExporter() {
//	    return OtlpHttpSpanExporter.builder()
//	                .build();
//	}

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
