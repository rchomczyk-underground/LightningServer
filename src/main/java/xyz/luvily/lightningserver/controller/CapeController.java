package xyz.luvily.lightningserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.luvily.lightningserver.model.Cape;
import xyz.luvily.lightningserver.model.User;
import xyz.luvily.lightningserver.service.CapeService;
import xyz.luvily.lightningserver.service.UserService;

@RestController()
@RequestMapping("/capes")
public class CapeController {

    private final CapeService capeService;
    private final UserService userService;

    @Autowired
    public CapeController(CapeService capeService, UserService userService) {
        this.capeService = capeService;
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping( "/texture/{identifier}")
    public ResponseEntity<Resource> getSpecifiedCape(@PathVariable int identifier) {
        Cape cape = capeService.getCape(identifier);
        if (cape == null) {
            return ResponseEntity.notFound().build();
        }

        ByteArrayResource resource = new ByteArrayResource(cape.getTexture());
        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }

    @GetMapping("/{username}.png")
    public ResponseEntity<Resource> getUserCape(@PathVariable String username) {
        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        Cape cape = capeService.getCape(user.getCape());
        if (cape == null) {
            return ResponseEntity.notFound().build();
        }

        ByteArrayResource resource = new ByteArrayResource(cape.getTexture());
        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }
}
