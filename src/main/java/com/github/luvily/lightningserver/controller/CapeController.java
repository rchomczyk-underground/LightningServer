package com.github.luvily.lightningserver.controller;

import com.github.luvily.lightningserver.model.Cape;
import com.github.luvily.lightningserver.model.User;
import com.github.luvily.lightningserver.service.CapeService;
import com.github.luvily.lightningserver.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CapeController {

    private final UserService userService;
    private final CapeService capeService;
    private final Gson gson;

    @Autowired
    public CapeController(UserService userService, CapeService capeService, Gson gson) {
        this.userService = userService;
        this.capeService = capeService;
        this.gson = gson;
    }

    @GetMapping(value = "/capes/texture/{identifier}")
    public ResponseEntity<?> getCape(@PathVariable String identifier) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");

        Cape cape = capeService.getCape(identifier);
        if (cape == null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", 404);
            jsonObject.addProperty("content", "Could not find cape with this identifier.");
            return new ResponseEntity<>(gson.toJson(jsonObject), httpHeaders, HttpStatus.NOT_FOUND);
        }

        httpHeaders.set("Content-Type", "image/png");

        ByteArrayResource resource = new ByteArrayResource(cape.getTexture());
        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/capes/{username}.png")
    public ResponseEntity<?> getUserCape(@PathVariable String username) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");

        User user = userService.getUser(username);
        if (user == null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", 404);
            jsonObject.addProperty("content", "Could not find user with matching username.");
            return new ResponseEntity<>(gson.toJson(jsonObject), httpHeaders, HttpStatus.NOT_FOUND);
        }

        Cape cape = capeService.getCape(user.getCape());
        if (cape == null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", 500);
            jsonObject.addProperty("content", "Woops? Something goes wrong here, this cape is not exists.");
            return new ResponseEntity<>(gson.toJson(jsonObject), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        httpHeaders.set("Content-Type", "image/png");

        ByteArrayResource resource = new ByteArrayResource(cape.getTexture());
        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }
}
