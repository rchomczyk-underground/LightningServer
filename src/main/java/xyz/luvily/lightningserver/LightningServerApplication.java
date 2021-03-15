package xyz.luvily.lightningserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableWebMvc
@EnableOpenApi
@EnableCaching
public class LightningServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LightningServerApplication.class, args);
    }
}
