package xyz.luvily.lightningserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.luvily.lightningserver.model.User;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ username: ?0 }")
    User findByUsername(String username);

    @Query("{ cape: ?0 }")
    List<User> findUsersByCape(int identifier);
}
