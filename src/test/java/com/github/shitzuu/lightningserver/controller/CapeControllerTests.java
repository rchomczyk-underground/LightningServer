package com.github.shitzuu.lightningserver.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class CapeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnEmptyResourceAsCape() throws Exception {
        this.mockMvc.perform(get("/capes/user-without-cape.png"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnDefaultCape() throws Exception {
        this.mockMvc.perform(get("/capes/shvtzuu.png"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
