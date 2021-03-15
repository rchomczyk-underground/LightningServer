package xyz.luvily.lightningserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.luvily.lightningserver.model.Cape;
import xyz.luvily.lightningserver.repository.CapeRepository;

import java.util.Objects;

@Service
@CacheConfig(cacheNames = { "capes" })
public class CapeService {

    private final CapeRepository repository;
    private final Cache cache;

    @Autowired
    public CapeService(CapeRepository repository, CacheManager cacheManager) {
        this.repository = repository;
        this.cache = cacheManager.getCache("capes");
    }

    @Cacheable
    public Cape getCape(int identifier) {
        if (cache.get(identifier) == null) {
            return repository.findByIdentifier(identifier);
        }

        return (Cape) Objects.requireNonNull(cache.get(identifier)).get();
    }
}
