package com.github.luvily.lightningserver.service;

import com.github.luvily.lightningserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.github.luvily.lightningserver.model.User;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "users", key = "#username", condition = "#username != null")
    public User getUser(String username) {
        return repository.findByUsername(username);
    }

    // This is not cached, because this will be exposed only for admin and should not be accessed to much.
    public List<User> getUsers() {
        return repository.findAll();
    }
}
