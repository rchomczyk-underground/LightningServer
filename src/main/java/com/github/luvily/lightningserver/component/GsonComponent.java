package com.github.luvily.lightningserver.component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GsonComponent {

    @Bean
    public Gson gson() {
        return new GsonBuilder().disableHtmlEscaping()
                .setPrettyPrinting()
                .create();
    }
}
