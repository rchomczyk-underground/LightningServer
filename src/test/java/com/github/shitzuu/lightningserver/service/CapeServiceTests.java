package com.github.shitzuu.lightningserver.service;

import com.github.shitzuu.lightningserver.domain.Cape;
import com.github.shitzuu.lightningserver.repository.CapeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

@SpringBootTest
class CapeServiceTests {

    private static final Cape TEST_ENTITY = new Cape("0001", new byte[0]);

    @Autowired
    private CapeService capeService;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void prepare() {
        CapeRepository capeRepository = capeService.getRepository();
        capeRepository.deleteAll();
        for (String cacheName : cacheManager.getCacheNames()) {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.invalidate();
            }
        }
    }

    @AfterEach
    public void clean() {
        CapeRepository capeRepository = capeService.getRepository();
        capeRepository.deleteAll();
        for (String cacheName : cacheManager.getCacheNames()) {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.invalidate();
            }
        }
    }

    @Test
    void puttingUserShouldReturnCape() {
        Assertions.assertEquals(TEST_ENTITY, capeService.putCape(TEST_ENTITY));
    }

    @Test
    void gettingUserShouldReturnNullIfNotExists() {
        Assertions.assertNull(capeService.getCape(TEST_ENTITY.getKey()));
    }

    @Test
    void gettingUserShouldReturnCorrectValueIfExists() {
        capeService.putCape(TEST_ENTITY);

        Cape cape = capeService.getCape(TEST_ENTITY.getKey());

        Assertions.assertNotNull(cape.getKey());
        Assertions.assertNotNull(cape.getContent());

        Assertions.assertEquals(TEST_ENTITY.getKey(), cape.getKey());
        Assertions.assertEquals(TEST_ENTITY.getContent().length, cape.getContent().length);
    }
}
