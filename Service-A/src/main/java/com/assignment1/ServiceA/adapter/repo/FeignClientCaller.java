package com.assignment1.ServiceA.adapter.repo;

import com.assignment1.ServiceA.adapter.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "Server-B/api-b")
public interface FeignClientCaller {
    @GetMapping("byName")
    String getNameById(@RequestParam String id) ;

    @PostMapping
     UserDto add(@RequestBody UserDto userDto);
}
