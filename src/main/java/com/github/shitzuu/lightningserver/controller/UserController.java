package com.github.shitzuu.lightningserver.controller;

import com.github.shitzuu.lightningserver.domain.User;
import com.github.shitzuu.lightningserver.service.UserService;
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

    @GetMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@PathVariable String username) {
        User user = userService.getUser(username);
        if (user == null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", 404);
            jsonObject.addProperty("content", "'" + username + "' does not exist.");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(gson.toJson(user), HttpStatus.OK);
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUsers() {
        return new ResponseEntity<>(gson.toJson(userService.getUsers()), HttpStatus.OK);
    }
}
