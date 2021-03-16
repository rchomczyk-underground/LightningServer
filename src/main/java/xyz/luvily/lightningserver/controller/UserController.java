package xyz.luvily.lightningserver.controller;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.luvily.lightningserver.model.User;
import xyz.luvily.lightningserver.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<String> getUser(@PathVariable String username) {
        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", user.getUsername());
        jsonObject.addProperty("cape", user.getCape());

        String jsonString = jsonObject.toString();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(jsonString.length())
                .body(jsonString);
    }
}
