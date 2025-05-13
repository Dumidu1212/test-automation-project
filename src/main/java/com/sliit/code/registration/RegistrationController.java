package com.sliit.code.registration;

import org.springframework.beans.factory.annotation.Autowired;
import com.sliit.code.registration.UserDTO;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sliit.code.registration.*;
@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            String message = registrationService.registerUser(userDTO);
            return ResponseEntity.status(201).body(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
