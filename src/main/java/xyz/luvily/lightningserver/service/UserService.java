package xyz.luvily.lightningserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.luvily.lightningserver.model.User;
import xyz.luvily.lightningserver.repository.UserRepository;

import java.util.Objects;

@Service
@CacheConfig(cacheNames = { "users" })
public class UserService {

    private final UserRepository repository;
    private final Cache cache;

    @Autowired
    public UserService(UserRepository repository, CacheManager cacheManager) {
        this.repository = repository;
        this.cache = cacheManager.getCache("users");
    }

    @Cacheable
    public User getUser(String username) {
        if (cache.get(username) == null) {
            return repository.findByUsername(username);
        }

        return (User) Objects.requireNonNull(cache.get(username)).get();
    }
}