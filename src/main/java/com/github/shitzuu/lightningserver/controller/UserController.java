package com.github.shitzuu.lightningserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.shitzuu.lightningserver.domain.User;
import com.github.shitzuu.lightningserver.service.UserService;
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
    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@PathVariable String username) throws JsonProcessingException {
        User user = userService.getUser(username);
        if (user == null) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("status", 404);
            objectNode.put("content", String.format("User with username '%s' does not exist.", username));
            return new ResponseEntity<>(objectMapper.writeValueAsString(objectNode), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(objectMapper.writeValueAsString(user), HttpStatus.OK);
    }
}
