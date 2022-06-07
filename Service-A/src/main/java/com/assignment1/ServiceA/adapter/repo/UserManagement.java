package com.assignment1.ServiceA.adapter.repo;

import com.assignment1.ServiceA.adapter.model.User;

import java.util.List;
import java.util.Optional;

public interface UserManagement {

    User getById(String id);

    String getNameById(String id);

    Optional<List<User>> getByName(String name);
}
