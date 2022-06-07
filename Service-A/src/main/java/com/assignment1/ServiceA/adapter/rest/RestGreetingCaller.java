package com.assignment1.ServiceA.adapter.rest;

import com.assignment1.ServiceA.adapter.dto.UserDto;
import com.assignment1.ServiceA.adapter.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api.rest")
public class RestGreetingCaller {

    private final RestTemplate restTemplate;

    public RestGreetingCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("id/{userId}")
    public String greetingWithId(@PathVariable() String userId) {
        return "Say Hi" + userId;
    }

    @GetMapping("{userId}")
    public String callGreetingWithName(@PathVariable() String userId) {
        String forObject = restTemplate.
            getForObject("http://Server-B/api-b/byName?id={id}",
                String.class, userId);
        return "You register in B but you are Welcome in A too  " + forObject;
    }


    @PostMapping
    public UserDto add(@RequestBody UserDto userDto) {
        HttpEntity<UserDto> request = new HttpEntity<>(userDto);
        restTemplate.postForObject("", request, User.class);
        return userDto;
    }

}
