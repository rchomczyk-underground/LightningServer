package com.github.shitzuu.lightningserver;

import com.github.shitzuu.lightningserver.domain.Cape;
import com.github.shitzuu.lightningserver.domain.User;
import com.github.shitzuu.lightningserver.repository.CapeRepository;
import com.github.shitzuu.lightningserver.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class LightningServerInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CapeRepository capeRepository;

    public LightningServerInitializer(UserRepository userRepository, CapeRepository capeRepository) {
        this.userRepository = userRepository;
        this.capeRepository = capeRepository;
    }

    @Override
    public void run(String... args) throws IOException {
        userRepository.deleteAll();
        userRepository.insert(new User("shvtzuu", "0001"));

        File file = new File("./assets/texture/0001.png");

        byte[] content = new byte[(int) file.length()];
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(file))) {
            inputStream.readFully(content);
        }

        capeRepository.deleteAll();
        capeRepository.insert(new Cape("0001", content));
    }
}
