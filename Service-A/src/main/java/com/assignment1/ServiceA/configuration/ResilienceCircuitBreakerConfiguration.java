package com.assignment1.ServiceA.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


@Configuration
public class ResilienceCircuitBreakerConfiguration {

    @Bean
    Customizer<Resilience4JCircuitBreakerFactory> customizer() {
        return factory -> {
            factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig
                    (
                        TimeLimiterConfig.custom()
                            .timeoutDuration(Duration.ofSeconds(2))
                            .build()
                    )
                .circuitBreakerConfig
                    (
                        CircuitBreakerConfig.custom()
                            .minimumNumberOfCalls(3)
                            .waitDurationInOpenState(Duration.ofSeconds(10))
                            .build()
                    )
                .build());
        };
    }
}
