package com.assignment1.ServiceA.repo;

import com.assignment1.ServiceA.model.User;

import java.util.List;
import java.util.Optional;

public interface UserManagement {

    User getById(String id);

    String getNameById(String id);

    Optional<List<User>> getByName(String name);
}
