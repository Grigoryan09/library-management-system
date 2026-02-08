package org.example.librarymanagementsystem.service;

import org.example.librarymanagementsystem.model.User;

import java.util.Optional;

public interface UserService {

    void  save(User user);

    Optional<User> findByUsername(String username);
}
