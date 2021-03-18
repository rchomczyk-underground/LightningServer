package com.github.luvily.lightningserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "users")
public class User implements Serializable {

    @Id
    private String id;
    private String username;
    private String cape;

    public User(String username, String cape) {
        this.username = username;
        this.cape = cape;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCape() {
        return cape;
    }

    public void setCape(String cape) {
        this.cape = cape;
    }

    public String getId() {
        return id;
    }
}
