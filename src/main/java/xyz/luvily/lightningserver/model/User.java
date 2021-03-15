package xyz.luvily.lightningserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    private final String username;
    private final int cape;

    public User(String username, int cape) {
        this.username = username;
        this.cape = cape;
    }

    public String getUsername() {
        return username;
    }

    public int getCape() {
        return cape;
    }

    @Override
    public String toString() {
        return "User (" + this.username + ")";
    }
}
