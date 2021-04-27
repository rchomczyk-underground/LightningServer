package com.github.shitzuu.lightningserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.github.shitzuu.lightningserver.domain.Cape;

@Repository
public interface CapeRepository extends MongoRepository<Cape, String> {

    @Query("{ key: ?0 }")
    Cape findOne(String key);
}
