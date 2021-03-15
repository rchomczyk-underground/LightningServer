package xyz.luvily.lightningserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.luvily.lightningserver.model.Cape;
import xyz.luvily.lightningserver.model.User;
import xyz.luvily.lightningserver.service.CapeService;
import xyz.luvily.lightningserver.service.UserService;

@RestController
public class CapeController {

    private final CapeService capeService;
    private final UserService userService;

    @Autowired
    public CapeController(CapeService capeService, UserService userService) {
        this.capeService = capeService;
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping(value = "/capes/texture/{identifier}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getSpecifiedCape(@PathVariable int identifier) {
        Cape cape = capeService.getCape(identifier);
        if (cape == null) {
            return new ResponseEntity<>(new byte[0], HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cape.getTexture(), HttpStatus.OK);
    }

    @GetMapping(value = "/capes/{raw}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getUserCape(@PathVariable String raw) {
        String username = raw.replace(".png", "");

        User user = userService.getUser(username);
        if (user == null) {
            return new ResponseEntity<>(new byte[0], HttpStatus.NOT_FOUND);
        }

        Cape cape = capeService.getCape(user.getCape());
        if (cape == null) {
            return new ResponseEntity<>(new byte[0], HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cape.getTexture(), HttpStatus.OK);
    }
}
