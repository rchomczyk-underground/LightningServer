package com.github.shitzuu.lightningserver.service;

import com.github.shitzuu.lightningserver.repository.UserRepository;
import org.jetbrains.annotations.VisibleForTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.github.shitzuu.lightningserver.domain.User;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "users", key = "#username")
    public User getUser(String username) {
        return repository.findOne(username);
    }

    public User putUser(User user) {
        return repository.save(user);
    }

    @VisibleForTesting
    public UserRepository getRepository() {
        return repository;
    }
}
