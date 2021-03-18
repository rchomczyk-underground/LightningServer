package xyz.luvily.lightningserver.service;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.luvily.lightningserver.model.User;
import xyz.luvily.lightningserver.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@CacheConfig(cacheNames = { "users" })
@Service
public class UserService {

    private final UserRepository repository;
    private final Cache cache;

    public UserService(UserRepository repository, CacheManager cacheManager) {
        this.repository = repository;
        this.cache      = cacheManager.getCache("users");
    }

    @Cacheable
    public User getUser(String username) {
        if (cache.get(username) == null) {
            return repository.findByUsername(username);
        }

        return (User) Objects.requireNonNull(cache.get(username)).get();
    }

    @SuppressWarnings("unchecked")
    @Cacheable
    public List<User> getUsers(int identifier) {
        if (cache.get(identifier) == null) {
            return repository.findUsersByCape(identifier);
        }

        return (List<User>) Objects.requireNonNull(cache.get(identifier)).get();
    }

    // This is not annotated with @Cacheable, because I want to have realtime data.
    public List<User> getUsers() {
        return repository.findAll();
    }

    public Cache getCache() {
        return cache;
    }

    public UserRepository getRepository() {
        return repository;
    }
}
