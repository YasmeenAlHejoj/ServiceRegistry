package com.assignment1.ServiceA.repo.impl;

import com.assignment1.ServiceA.model.User;
import com.assignment1.ServiceA.repo.UserManagement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InMemoryUser implements UserManagement {


    private final List<User> users;

    public InMemoryUser() {
        users = new ArrayList<>();
        users.add(new User("1", "moh", 40));
        users.add(new User("2", "yas", 30));
        users.add(new User("3", "qq", 20));
        users.add(new User("4", "qwqwqwqwqw", 10));
    }


    @Override
    public User getById(String id) {
        User user = users.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst().orElseThrow(() -> new RuntimeException("User Not Found"));
        return user;
    }

    @Override
    public String getNameById(String id) {
        String first = users.stream()
            .filter(u -> u.getId().equals(id))
            .map(User::getName).toList().get(0);
        return first;
    }

    @Override
    public Optional<List<User>> getByName(String name) {
        List<User> collect = users.stream()
            .filter(u -> u.getName().equals(name))
            .collect(Collectors.toList());
        return Optional.ofNullable(collect);
    }


}
