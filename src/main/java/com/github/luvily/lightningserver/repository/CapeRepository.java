package com.github.luvily.lightningserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.github.luvily.lightningserver.model.Cape;

@Repository
public interface CapeRepository extends MongoRepository<Cape, String> {

    @Query("{ identifier: ?0 }")
    Cape findOne(String identifier);
}
