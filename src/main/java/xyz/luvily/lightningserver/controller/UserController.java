package xyz.luvily.lightningserver.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.luvily.lightningserver.model.Cape;
import xyz.luvily.lightningserver.model.User;
import xyz.luvily.lightningserver.repository.UserRepository;
import xyz.luvily.lightningserver.service.CapeService;
import xyz.luvily.lightningserver.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final CapeService capeService;

    private final Gson gson;

    @Autowired
    public UserController(UserService userService, CapeService capeService, Gson gson) {
        this.userService = userService;
        this.capeService = capeService;
        this.gson        = gson;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUsers() {
        return new ResponseEntity<>(gson.toJson(userService.getUsers()), HttpStatus.OK);
    }

    @SuppressWarnings("Duplicates")
    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@PathVariable(value = "username") String raw) {
        String username = raw.replace(".png", "");
        if (username.contains("_") || username.length() < 3 || username.length() > 16) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", 400);
            jsonObject.addProperty("message", "Username cannot contain '_' and should be 3-16 characters long");
            return new ResponseEntity<>(gson.toJson(jsonObject), HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUser(username);
        if (user == null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", 404);
            jsonObject.addProperty("message", "Could not find a user named '" + username + "'");
            return new ResponseEntity<>(gson.toJson(jsonObject), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(gson.toJson(user), HttpStatus.OK);
    }

    @SuppressWarnings("Duplicates")
    @GetMapping(value = "/cape/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUsers(@PathVariable(value = "identifier") int identifier) {
        Cape cape = capeService.getCape(identifier);
        if (cape == null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", 404);
            jsonObject.addProperty("message", "Could not find a cape with '" + identifier + "' identifier.");
            return new ResponseEntity<>(gson.toJson(jsonObject), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(gson.toJson(userService.getUsers(identifier)), HttpStatus.OK);
    }
}
