package com.github.luvily.lightningserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "capes")
@Data
public class Cape implements Serializable {

    @Id
    private String id;
    private final String identifier;
    private final byte[] texture;
}
