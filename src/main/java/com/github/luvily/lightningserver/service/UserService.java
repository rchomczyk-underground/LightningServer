package com.github.luvily.lightningserver.service;

import com.github.luvily.lightningserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
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

    @Nullable
    @Cacheable(value = "users", key = "#username", condition = "#username != null")
    public User getUser(String username) {
        return repository.findOne(username);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }
}
