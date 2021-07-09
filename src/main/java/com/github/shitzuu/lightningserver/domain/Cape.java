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
@Document(collection = "capes")
public class Cape implements Serializable {

    @JsonIgnore
    @Id
    private transient ObjectId id;

    private final String key;
    private final byte[] content;
}
