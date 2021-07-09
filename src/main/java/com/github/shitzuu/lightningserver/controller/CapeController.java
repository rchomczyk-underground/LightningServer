package com.github.shitzuu.lightningserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.shitzuu.lightningserver.domain.Cape;
import com.github.shitzuu.lightningserver.domain.User;
import com.github.shitzuu.lightningserver.service.CapeService;
import com.github.shitzuu.lightningserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CapeController {

    private static final Resource EMPTY_RESOURCE = new ByteArrayResource(new byte[0]);

    private final CapeService capeService;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CapeController(CapeService capeService, UserService userService, ObjectMapper objectMapper) {
        this.capeService = capeService;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/capes/{username}.png", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getCape(@PathVariable String username) {
        User user = userService.getUser(username);
        if (user == null) {
            return new ResponseEntity<>(EMPTY_RESOURCE, HttpStatus.NOT_FOUND);
        }

        Cape cape = capeService.getCape(user.getCape());
        if (cape == null) {
            return new ResponseEntity<>(EMPTY_RESOURCE, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new ByteArrayResource(cape.getContent()), HttpStatus.OK);
    }

    @GetMapping(value = "/capes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCapes() throws JsonProcessingException {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("amount", capeService.getCapes().size());

        ArrayNode arrayNode = objectMapper.createArrayNode();
        for (Cape cape : capeService.getCapes()) {
            arrayNode.add(cape.getKey());
        }

        objectNode.set("keys", arrayNode);
        return new ResponseEntity<>(objectMapper.writeValueAsString(objectNode), HttpStatus.OK);
    }
}
