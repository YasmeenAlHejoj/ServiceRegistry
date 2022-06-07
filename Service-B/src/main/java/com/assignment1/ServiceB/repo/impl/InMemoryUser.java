package com.assignment1.ServiceB.repo.impl;

import com.assignment1.ServiceB.model.User;
import com.assignment1.ServiceB.repo.UserManagement;
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
    public String getNameById(String id) {
        return users.stream()
            .filter(u -> u.getId().equals(id))
            .map(User::getName)
            .findFirst().orElse("User Not Found");
    }

    @Override
    public User insert(User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return users.stream().toList();
    }
}
