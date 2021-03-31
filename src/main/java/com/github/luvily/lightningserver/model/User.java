package com.github.luvily.lightningserver.model;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "users")
@Data
public class User implements Serializable {

    @Id
    private String id;
    private final String username;
    private final String cape;

    public JsonObject toJson() {
        return Json.object()
                .add("username", username)
                .add("cape", cape);
    }
}
