package com.github.shitzuu.lightningserver.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "capes")
public class Cape implements Serializable {

    @Id
    private transient ObjectId id;

    private final String key;
    private final byte[] content;

    public Cape(String key, byte[] content) {
        this.key = key;
        this.content = content;
    }

    public ObjectId getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public byte[] getContent() {
        return content;
    }
}
