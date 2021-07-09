package com.github.shitzuu.lightningserver.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.github.shitzuu.lightningserver.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ username: ?0 }")
    User findOne(@NotNull String username);
}
