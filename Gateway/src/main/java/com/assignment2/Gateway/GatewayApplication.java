package com.assignment2.Gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("service_route_java_config", r -> r.path("/service/**")
                .filters(f ->
                    f.rewritePath("/service(?<segment>/?.*)", "$\\{segment}"))
                .uri("http://localhost:2020"))

            .route("service_route_java_config", r -> r.path("/xx/**")
                .filters(f ->
                    f.rewritePath("/xx(?<segment>/?.*)", "$\\{segment}"))
                .uri("lb://server-A"))
            .build();

    }
}
