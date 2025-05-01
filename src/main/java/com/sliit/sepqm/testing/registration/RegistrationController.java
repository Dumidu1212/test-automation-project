package com.sliit.sepqm.testing.registration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {
    private final RegistrationService svc;

    public RegistrationController(RegistrationService svc) {
        this.svc = svc;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest req) {
        svc.register(req);
        return ResponseEntity.status(201).body(Map.of("message", "Registration complete"));
    }
}
