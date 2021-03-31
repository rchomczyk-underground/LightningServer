package com.github.luvily.lightningserver.controller;

import com.github.luvily.lightningserver.model.Cape;
import com.github.luvily.lightningserver.model.User;
import com.github.luvily.lightningserver.service.CapeService;
import com.github.luvily.lightningserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class CapeController {

    private final UserService userService;
    private final CapeService capeService;

    @GetMapping(value = "/capes/texture/{identifier}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getCape(@PathVariable String identifier) {
        Cape cape = capeService.getCape(identifier);
        if (cape == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ByteArrayResource(cape.getTexture()));
    }

    @GetMapping(value = "/capes/{username}.png", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getUserCape(@PathVariable String username) {
        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        Cape cape = capeService.getCape(user.getCape());
        if (cape == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(new ByteArrayResource(cape.getTexture()));
    }
}
