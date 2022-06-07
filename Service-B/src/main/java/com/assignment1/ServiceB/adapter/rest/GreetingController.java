package com.assignment1.ServiceB.adapter.rest;

import com.assignment1.ServiceB.model.User;
import com.assignment1.ServiceB.model.UserDto;
import com.assignment1.ServiceB.repo.UserManagement;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api-b")
public class GreetingController {
    private final UserManagement userManagement;

    public GreetingController(UserManagement userManagement) {
        this.userManagement = userManagement;
    }

    @GetMapping("byName")
    public String getNameById(@RequestParam String id) {
        return userManagement.getNameById(id);
    }

    @GetMapping()
    public List<UserDto> getAll() {
        return userManagement
            .getAll()
            .stream()
            .map(toUserDto()).collect(Collectors.toList());
    }

    @PostMapping
    public UserDto add(@RequestBody UserDto userDto) {
        User insert = userManagement.insert(toUserModel().apply(userDto));
        return toUserDto().apply(insert);
    }

    private Function<UserDto, User> toUserModel() {
        return (userDto -> new User(userDto.getId(), userDto.getName(), userDto.getAge()));
    }

    private Function<User, UserDto> toUserDto() {
        return (user -> new UserDto(user.getId(), user.getName(), user.getAge()));

    }
}
