package com.github.luvily.lightningserver.service;

import com.github.luvily.lightningserver.repository.CapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import com.github.luvily.lightningserver.model.Cape;

@Service
public class CapeService {

    private final CapeRepository repository;

    @Autowired
    public CapeService(CapeRepository repository) {
        this.repository = repository;
    }

    @Nullable
    @Cacheable(value = "capes", key = "#identifier", condition = "#identifier != null")
    public Cape getCape(String identifier) {
        return repository.findOne(identifier);
    }
}
