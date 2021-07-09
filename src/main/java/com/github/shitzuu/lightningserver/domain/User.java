package com.github.shitzuu.lightningserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@Document(collection = "users")
public class User implements Serializable {

    @JsonIgnore
    @Id
    private transient ObjectId id;

    private final String username;
    private final String cape;
}
