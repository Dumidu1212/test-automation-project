package com.sliit.sepqm.testing.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final LoginService svc;

    public LoginController(LoginService svc) {
        this.svc = svc;
    }

    @PostMapping(
            value    = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, String>> login(@RequestBody Credentials creds) {
        try {
            String token = svc.login(creds);
            return ResponseEntity
                    .ok(Map.of("token", token));
        } catch (IllegalArgumentException e) {
            // service throws this on bad credentials
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}
