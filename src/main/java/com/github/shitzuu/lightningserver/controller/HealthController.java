package com.github.shitzuu.lightningserver.controller;

import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping(value = "/health")
    public ResponseEntity<String> getHealth() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", "up");
        return ResponseEntity.ok(jsonObject.toString());
    }
}
