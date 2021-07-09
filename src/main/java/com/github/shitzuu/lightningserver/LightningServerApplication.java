package com.github.shitzuu.lightningserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LightningServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LightningServerApplication.class, args);
    }
}
