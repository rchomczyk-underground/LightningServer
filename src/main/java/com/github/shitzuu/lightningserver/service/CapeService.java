package com.github.shitzuu.lightningserver.service;

import com.github.shitzuu.lightningserver.repository.CapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.github.shitzuu.lightningserver.domain.Cape;

import java.util.List;

@Service
public class CapeService {

    private final CapeRepository repository;

    @Autowired
    public CapeService(CapeRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "capes", key = "#key")
    public Cape getCape(String key) {
        return repository.findOne(key);
    }

    // This isn't cached, because we need this data in real-time.
    public List<Cape> getCapes() {
        return repository.findAll();
    }
}
