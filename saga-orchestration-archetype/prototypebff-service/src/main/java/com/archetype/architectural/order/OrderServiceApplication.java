package com.archetype.architectural.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

//@OpenAPIDefinition(info = @Info(
//        title = "Spring WebFlux CRUD Example",
//        version = "1.0",
//        description = "Spring WebFlux CRUD Example Sample documents"
//))
//@EnableR2dbcRepositories
@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

//    @Bean
//    ApplicationRunner init(PurchaseOrderRepository repository, @SuppressWarnings("deprecation") DatabaseClient client) {
//        return args -> {
//            client.execute("create table IF NOT EXISTS PurchaseOrder" +
//                    "(id SERIAL PRIMARY KEY, text varchar (255) not null, completed boolean default false);").fetch().first().subscribe();
//            client.execute("DELETE FROM TODO;").fetch().first().subscribe();
//
//            Stream<PurchaseOrder> stream = Stream.of(
//                    new PurchaseOrder(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"),1,1, 1.1d, OrderStatus.ORDER_CREATED),
//                    new PurchaseOrder(UUID.randomUUID(),1,1, 1.1d, OrderStatus.ORDER_CREATED));
//
//            // initialize the database
//
//            repository.saveAll(Flux.fromStream(stream))
//                    .then()
//                    .subscribe(); // execute
//
//        };
//    }
}
