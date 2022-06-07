package com.assignment1.ServiceB.repo;

import com.assignment1.ServiceB.model.User;

import java.util.List;
import java.util.Optional;

public interface UserManagement {
    String getNameById(String id);

    User insert(User user);

    List<User> getAll();
}
