package com.archetype.architectural.parameter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
//@SpringBootApplication(scanBasePackages = {"com.archetype"} )
public class ParametrosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParametrosApplication.class, args);
    }

}
