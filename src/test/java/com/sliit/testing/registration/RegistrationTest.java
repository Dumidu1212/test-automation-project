package com.sliit.testing.registration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sliit.code.RegistrationMain;
import com.sliit.code.database.UserRepository;
import com.sliit.code.registration.User;
import com.sliit.code.registration.UserDTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = RegistrationMain.class)
@AutoConfigureMockMvc
public class RegistrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void cleanup() {
        userRepository.deleteAll(); // Ensures each test runs in isolation
    }

    @Test
    @DisplayName("POST /api/register with valid user → 201 CREATED")
    void whenValidUser_thenCreate() throws Exception {
        UserDTO userDTO = new UserDTO(
                "Alice",
                "alice@example.com",
                "123456",
                "Colombo",
                "student"
        );

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Registration complete"));

        assertTrue(userRepository.existsByEmail("alice@example.com"));
    }

    @Test
    @DisplayName("POST /api/register with duplicate email → 400 BAD REQUEST")
    void whenDuplicateEmail_thenError() throws Exception {
        // Arrange: Insert existing user
        User existingUser = new User("Bob", "bob@example.com", "encodedPass", "Galle", "teacher");
        userRepository.save(existingUser);

        // Act & Assert
        UserDTO userDTO = new UserDTO(
                "Bob",
                "bob@example.com",
                "abcdef",
                "Galle",
                "teacher"
        );

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email already registered"));
    }
}
