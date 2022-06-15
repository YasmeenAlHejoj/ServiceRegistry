package com.assignment1.ServiceA.adapter.rest;

import com.assignment1.ServiceA.adapter.rest.dto.UserDto;
import com.assignment1.ServiceA.repo.FeignClientCaller;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api.feign")
public class FeignGreetingCaller {
    private final FeignClientCaller feignClientCaller;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public FeignGreetingCaller(FeignClientCaller feignClientCaller,
                               CircuitBreakerFactory circuitBreakerFactory) {
        this.feignClientCaller = feignClientCaller;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @GetMapping("{userId}")
    public String callGreetingWithName(@PathVariable() String userId) {

        return circuitBreakerFactory
            .create("call-withName")
            .run(() ->
                {
                    String nameById = feignClientCaller.getNameById(userId);
                    if(nameById.startsWith("A")){
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                           // throw new RuntimeException(e);
                        }
                    }
                    return "You register in B but you are Welcome in A too  " + nameById;
                },exception->"hello"
            );
    }


    @PostMapping
    public UserDto add(@RequestBody UserDto userDto) {
        return circuitBreakerFactory
            .create("add-user")
            .run(() -> feignClientCaller.add(userDto));
        // return feignClientCaller.add(userDto);
    }

}
