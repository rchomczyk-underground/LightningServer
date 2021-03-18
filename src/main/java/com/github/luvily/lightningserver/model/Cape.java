package com.github.luvily.lightningserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "capes")
public class Cape implements Serializable {

    @Id
    private String id;
    private String identifier;
    private byte[] texture;

    public Cape(String identifier, byte[] texture) {
        this.identifier = identifier;
        this.texture = texture;
    }

    public Cape() {

    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public byte[] getTexture() {
        return texture;
    }

    public void setTexture(byte[] texture) {
        this.texture = texture;
    }

    public String getId() {
        return id;
    }
}
