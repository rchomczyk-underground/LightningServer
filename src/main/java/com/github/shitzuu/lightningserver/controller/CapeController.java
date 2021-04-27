package com.github.shitzuu.lightningserver.controller;

import com.github.shitzuu.lightningserver.domain.Cape;
import com.github.shitzuu.lightningserver.domain.User;
import com.github.shitzuu.lightningserver.service.CapeService;
import com.github.shitzuu.lightningserver.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class CapeController {

    private static final Resource EMPTY_RESOURCE = new ByteArrayResource(new byte[0]);

    private final CapeService capeService;
    private final UserService userService;
    private final Gson gson;

    @Autowired
    public CapeController(CapeService capeService, UserService userService, Gson gson) {
        this.capeService = capeService;
        this.userService = userService;
        this.gson = gson;
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
    public ResponseEntity<String> getCapes() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("amount", capeService.getCapes().size());
        jsonObject.add("keys", gson.toJsonTree(capeService.getCapes()
                .stream()
                .map(Cape::getKey)
                .collect(Collectors.toList())));
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @PostMapping(value = "/capes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertCape() {
        return null;
    }
}
