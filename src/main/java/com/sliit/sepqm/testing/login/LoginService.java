package com.sliit.sepqm.testing.login;

import com.sliit.sepqm.testing.database.User;
import com.sliit.sepqm.testing.database.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public LoginService(UserRepository repo) {
        this.repo = repo;
    }

    public String login(Credentials c) {
        User u = repo.findByEmail(c.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        if (!encoder.matches(c.getPassword(), u.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return java.util.UUID.randomUUID().toString();
    }
}
