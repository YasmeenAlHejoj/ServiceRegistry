package com.assignment1.ServiceA.adapter.rest;

import com.assignment1.ServiceA.adapter.rest.dto.UserDto;
import com.assignment1.ServiceA.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api.rest")
public class RestGreetingCaller {

    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Value("${restTemplate.serviceB.url}")
    private String urlPath;

    public RestGreetingCaller(RestTemplate restTemplate,
                              CircuitBreakerFactory circuitBreakerFactory) {
        this.restTemplate = restTemplate;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @GetMapping("id/{userId}")
    public String greetingWithId(@PathVariable() String userId) {
        return circuitBreakerFactory.create("greet-id").run(
            () -> "Say Hi" + userId
        );
        // return "Say Hi" + userId;
    }

    @GetMapping("{userId}")
    public String callGreetingWithName(@PathVariable() String userId) {
        return circuitBreakerFactory.create("greet-name").run(
            () -> {
                String forObject = restTemplate.
                    getForObject(urlPath + "/byName?id={id}",
                        String.class, userId);
                return "You register in B but you are Welcome in A too  " + forObject;
            }
        );

    }


    @PostMapping
    public UserDto add(@RequestBody UserDto userDto) {
        HttpEntity<UserDto> request = new HttpEntity<>(userDto);
        restTemplate.postForObject("", request, User.class);
        return userDto;
    }

}
