package com.sliit.sepqm.testing.login;

import com.sliit.sepqm.testing.database.User;
import com.sliit.sepqm.testing.database.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class LoginTests {
    @Autowired
    MockMvc mvc;
    @Autowired
    UserRepository repo;

    @BeforeEach
    void setupUser() {
        String hash = new BCryptPasswordEncoder().encode("plaintext");
        repo.save(new User("Carol","carol@example.com",hash,"L","USER"));
    }

    @AfterEach
    void cleanup() {
        repo.deleteAll();
    }

    @Test
    void whenValidLogin_then200() throws Exception {
        String j = "{\"email\":\"carol@example.com\",\"password\":\"plaintext\"}";
        mvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(j))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    void whenInvalidPassword_then401() throws Exception {
        String j = "{\"email\":\"carol@example.com\",\"password\":\"wrong\"}";
        mvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(j))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid username or password"));
    }

}
