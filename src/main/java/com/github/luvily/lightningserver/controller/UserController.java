package com.github.luvily.lightningserver.controller;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.github.luvily.lightningserver.model.User;
import com.github.luvily.lightningserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUsers() {
        JsonArray jsonArray = Json.array();
        for (User user : userService.getUsers()) {
            jsonArray.add(user.toJson());
        }

        return ResponseEntity.ok(jsonArray.toString());
    }

    @GetMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@PathVariable String username) {
        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.toJson().toString());
    }
}
