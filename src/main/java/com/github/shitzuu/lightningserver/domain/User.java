package com.github.shitzuu.lightningserver.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "users")
public class User implements Serializable {

    @Id
    private transient ObjectId id;

    private final String username;
    private final String cape;

    public User(String username, String cape) {
        this.username = username;
        this.cape = cape;
    }

    public ObjectId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCape() {
        return cape;
    }
}
