package com.assignment1.ServiceA.adapter.rest;

import com.assignment1.ServiceA.adapter.dto.UserDto;
import com.assignment1.ServiceA.adapter.repo.FeignClientCaller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api.feign")
public class FeignGreetingCaller {
    private final FeignClientCaller feignClientCaller;

    public FeignGreetingCaller(FeignClientCaller feignClientCaller) {
        this.feignClientCaller = feignClientCaller;
    }
    @GetMapping("{userId}")
    public String callGreetingWithName(@PathVariable() String userId) {
        String forObject = feignClientCaller.getNameById(userId);
        return "You register in B but you are Welcome in A too  " + forObject;
    }


    @PostMapping
    public UserDto add(@RequestBody UserDto userDto){
        return  feignClientCaller.add(userDto);
    }

}
