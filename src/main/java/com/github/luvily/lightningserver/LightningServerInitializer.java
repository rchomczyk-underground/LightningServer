package com.github.luvily.lightningserver;

import com.github.luvily.lightningserver.model.Cape;
import com.github.luvily.lightningserver.model.User;
import com.github.luvily.lightningserver.repository.CapeRepository;
import com.github.luvily.lightningserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class LightningServerInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CapeRepository capeRepository;

    @Override
    public void run(final String... arguments) throws IOException {
        capeRepository.deleteAll();
        try (InputStream inputStream = new FileInputStream("./assets/sample/0001.png")) {
            capeRepository.insert(new Cape("0001", inputStream.readAllBytes()));
        }

        userRepository.deleteAll();
        userRepository.insert(new User("luvily", "0001"));
        userRepository.insert(new User("shitzuu", "0001"));
    }
}
