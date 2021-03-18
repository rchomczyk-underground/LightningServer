package com.github.luvily.lightningserver.controller;

import com.github.luvily.lightningserver.model.User;
import com.github.luvily.lightningserver.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    private final Gson gson;

    @Autowired
    public UserController(UserService userService, Gson gson) {
        this.userService = userService;
        this.gson = gson;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUsers() {
        return new ResponseEntity<>(gson.toJson(userService.getUsers()), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@PathVariable String username) {
        User user = userService.getUser(username);
        if (user == null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", 404);
            jsonObject.addProperty("content", "Could not find user with matching username.");
            return new ResponseEntity<>(gson.toJson(jsonObject), HttpStatus.NOT_FOUND);
        }

        String jsonString = gson.toJson(user);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", 200);
        jsonObject.add("content", gson.fromJson(jsonString, JsonObject.class));
        return new ResponseEntity<>(gson.toJson(jsonObject), HttpStatus.OK);
    }
}
