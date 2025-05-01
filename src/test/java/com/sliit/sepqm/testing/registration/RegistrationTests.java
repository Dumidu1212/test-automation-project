package com.sliit.sepqm.testing.registration;

import com.sliit.sepqm.testing.database.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class RegistrationTests {
    @Autowired
    MockMvc mvc;
    @Autowired
    UserRepository repo;

    @AfterEach
    void cleanup() {
        repo.deleteAll();
    }

    @Test
    void whenValidRegistration_then201() throws Exception {
        String json = "{\"name\":\"Alice\",\"email\":\"alice@example.com\",\"password\":\"P@ssw0rd!\",\"location\":\"X\",\"role\":\"USER\"}";
        mvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Registration complete"));
        assertThat(repo.existsByEmail("alice@example.com")).isTrue();
    }

    @Test
    void whenDuplicateEmail_then400() throws Exception {
        repo.save(new com.sliit.sepqm.testing.database.User("Bob","bob@example.com","pw","L","ADMIN"));
        String dup = "{\"name\":\"Bob\",\"email\":\"bob@example.com\",\"password\":\"pw2\",\"location\":\"L2\",\"role\":\"ADMIN\"}";
        mvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dup))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Email already registered"));
    }
}
