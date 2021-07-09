package com.github.shitzuu.lightningserver;

import com.github.shitzuu.lightningserver.controller.CapeController;
import com.github.shitzuu.lightningserver.controller.UserController;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LightningServerTests {

    @Autowired
    private CapeController capeController;

    @Autowired
    private UserController userController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(capeController);
        Assertions.assertNotNull(userController);
    }
}
