package com.github.shitzuu.lightningserver.service;

import com.github.shitzuu.lightningserver.domain.User;
import com.github.shitzuu.lightningserver.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

@SpringBootTest
class UserServiceTests {

    private static final User TEST_ENTITY = new User("shvtzuu", "0001");

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void prepare() {
        UserRepository userRepository = userService.getRepository();
        userRepository.deleteAll();
        for (String cacheName : cacheManager.getCacheNames()) {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.invalidate();
            }
        }
    }

    @AfterEach
    public void clean() {
        UserRepository userRepository = userService.getRepository();
        userRepository.deleteAll();
        for (String cacheName : cacheManager.getCacheNames()) {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.invalidate();
            }
        }
    }

    @Test
    void puttingUserShouldReturnUser() {
        Assertions.assertEquals(TEST_ENTITY, userService.putUser(TEST_ENTITY));
    }

    @Test
    void gettingUserShouldReturnNullIfNotExists() {
        Assertions.assertNull(userService.getUser(TEST_ENTITY.getUsername()));
    }

    @Test
    void gettingUserShouldReturnCorrectValueIfExists() {
        userService.putUser(TEST_ENTITY);

        User user = userService.getUser(TEST_ENTITY.getUsername());

        Assertions.assertNotNull(user.getUsername());
        Assertions.assertNotNull(user.getCape());

        Assertions.assertEquals(TEST_ENTITY.getUsername(), user.getUsername());
        Assertions.assertEquals(TEST_ENTITY.getCape(), user.getCape());
    }
}
