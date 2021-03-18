package xyz.luvily.lightningserver.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.luvily.lightningserver.model.Cape;
import xyz.luvily.lightningserver.model.User;
import xyz.luvily.lightningserver.repository.CapeRepository;
import xyz.luvily.lightningserver.service.CapeService;
import xyz.luvily.lightningserver.service.UserService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/capes")
public class CapeController {

    private final UserService userService;
    private final CapeService capeService;

    private final Gson gson;

    @Autowired
    public CapeController(UserService userService, CapeService capeService, Gson gson) {
        this.userService = userService;
        this.capeService = capeService;
        this.gson        = gson;
    }

    @SuppressWarnings("Duplicates")
    @GetMapping(value = "/{username}.png")
    public ResponseEntity<?> getCape(@PathVariable(value = "username") String raw) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");

        String username = raw.replace(".png", "");
        if (username.contains("_") || username.length() < 3 || username.length() > 16) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", 400);
            jsonObject.addProperty("message", "Username cannot contain '_' and should be 3-16 characters long");
            return new ResponseEntity<>(gson.toJson(jsonObject), httpHeaders, HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUser(username);
        if (user == null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", 404);
            jsonObject.addProperty("message", "Could not find a user named '" + username + "'");
            return new ResponseEntity<>(gson.toJson(jsonObject), httpHeaders, HttpStatus.NOT_FOUND);
        }

        Cape cape = capeService.getCape(user.getCape());
        if (cape == null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", 404);
            jsonObject.addProperty("message", "Could not find a cape with '" + user.getCape() + "' identifier.");
            return new ResponseEntity<>(gson.toJson(jsonObject), httpHeaders, HttpStatus.NOT_FOUND);
        }

        // Changing content type to 'image/png'
        httpHeaders.set("Content-Type", "image/png");

        ByteArrayResource resource = new ByteArrayResource(cape.getTexture());
        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }

    @SuppressWarnings("Duplicates")
    @GetMapping(value = "/texture/{identifier}")
    public ResponseEntity<?> getCape(@PathVariable(value = "identifier") int identifier) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");

        Cape cape = capeService.getCape(identifier);
        if (cape == null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", 404);
            jsonObject.addProperty("message", "Could not find a cape with '" + identifier + "' identifier.");
            return new ResponseEntity<>(gson.toJson(jsonObject), httpHeaders, HttpStatus.NOT_FOUND);
        }

        // Changing content type to 'image/png'
        httpHeaders.set("Content-Type", "image/png");

        ByteArrayResource resource = new ByteArrayResource(cape.getTexture());
        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "/texture", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCape(@RequestParam(value = "file") MultipartFile file) throws IOException {
        CapeRepository repository = capeService.getRepository();

        int identifier = 1;

        List<Cape> capes = repository.findAll(Sort.by(Sort.Direction.ASC, "identifier"));
        if (capes.size() > 0) {
            identifier = capes.size() + 1;
        }

        repository.insert(new Cape(identifier, file.getBytes()));

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", 200);
        jsonObject.addProperty("message", "Success! Your cape is now identified by " + identifier + " id.");
        return new ResponseEntity<>(gson.toJson(jsonObject), HttpStatus.OK);
    }
}
